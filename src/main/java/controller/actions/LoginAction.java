/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package controller.actions;

import util.SiGLa;
import util.ActiveDirectory;
import dao.GrupoDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Grupo;
import model.Pessoa;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("com.sun.jndi.ldap.connect.pool.timeout", "1800000");
            System.setProperty("javax.net.ssl.trustStore", SiGLa.KEYSTORE);
            System.setProperty("javax.net.ssl.keyStore", SiGLa.KEYSTORE);

            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            p.setUsername(request.getParameter("username").replaceAll("[0-9]", ""));
            p.setSenha(request.getParameter("password"));

            if (ad.login(p)) {
                session.setAttribute("ad", ad);
                GrupoDAO gdao = new GrupoDAO();
                ArrayList<Grupo> arrayg = gdao.select();
                int c = 0;

                for (Grupo g : arrayg) {
                    c++;
                    if (ad.isMember(p, g)) {
                        p.setRole(g.getRole());
                        break;
                    }

                    if (c == arrayg.size()) {
                        session.setAttribute("msg", "Voc&ecirc; n&atilde;o tem permiss&atilde;o de acesso");
                        session.setAttribute("status", "error");
                        ad.closeLdapConnection();
                        return request.getContextPath();
                    }
                }

                ArrayList<Pessoa> ps = ad.getProfessores();
                session.setAttribute("todos-usuarios", ps);

                p.setNome(ad.getGivenName(p));
                p.setNomeCompleto(ad.getCN(p));
                p.setCargo(ad.getTitle(p));
                p.setDepto(ad.getDepartment(p));
                p.setEmail(ad.getMail(p));
                p.setShownName(p.getNome() + " " + p.getNomeCompleto().substring(p.getNomeCompleto().lastIndexOf(" ") + 1));

                session.setAttribute("pessoa", p);
                return request.getContextPath() + "/pagina/home";
            }
        } catch (CommunicationException e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao contactar a controladora de dom&iacute;nio");
            session.setAttribute("status", "error");
            System.out.println("Erro ao conectar: CommunicationException - Erro ao contactar a controladora de domínio");
            return request.getContextPath();
        } catch (AuthenticationException e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Credenciais de acesso incorretas");
            session.setAttribute("status", "error");
            System.out.println("Erro ao conectar: AuthenticationException - Credenciais incorretas");
            return request.getContextPath();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("exception", e);
            System.out.println("Erro ao conectar: " + e.getMessage());
            return request.getContextPath() + "/error/error";
        }

        session.setAttribute("msg", "Erro ao fazer login");
        session.setAttribute("status", "error");
        return request.getContextPath();
    }
}
