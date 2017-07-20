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
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();

            p.setUsername(request.getParameter("username").replaceAll("[0-9]", "")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            String path = "C:/img/users/" + p.getUsername() + "_pic.jpg";
            FileOutputStream pic = new FileOutputStream(new File(path));

            if (ad.login(p)) { // faz o login
                GrupoDAO gdao = new GrupoDAO();
                ArrayList<Grupo> arrayg = gdao.select();

                p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo                
                p.setCargo(ad.getTitle(p)); // passa o atributo de cargo
                p.setDepto(ad.getDepartment(p)); // passa o atributo de cargo 
                p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email   

                if (p.getNomeCompleto().equals("Ricardo Morales Miranda")) {
                    p.setShownName("Ricardo Morales");
                } else if (p.getNomeCompleto().equals("Jose Eduardo Morello Lobo")) {
                    p.setNome("Eduardo");
                    p.setShownName("Eduardo Lobo");
                } else {
                    p.setShownName(p.getNome() + " " + p.getNomeCompleto().substring(p.getNomeCompleto().lastIndexOf(" ") + 1));
                }

                boolean acesso = false;
                for (Grupo g : arrayg) {
                    g.setGrupo(arrayg.get(arrayg.indexOf(g)).getGrupo());
                    if (ad.isMember(p, arrayg.get(arrayg.indexOf(g)))) {
                        p.setRole(g.getRole());
                        acesso = true;
                    }
                }

                ArrayList<Pessoa> ps = ad.getData();
                session.setAttribute("todos-usuarios", ps);

                if (acesso) {
                    session.setAttribute("ad", ad); // salva dados do AD na sessão
                    session.setAttribute("pessoa", p); // salva dados do login na sessão
                    return request.getContextPath() + "/pagina/home";
                } else {
                    session.setAttribute("msg", "Voc&ecirc; n&atilde;o tem permiss&atilde;o de acesso");
                    return request.getContextPath(); // chama de volta a página de login
                }
            }
        } catch (CommunicationException e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao contactar a controladora de dom&iacute;nio");
            return request.getContextPath(); // chama de volta a página de login
        } catch (AuthenticationException e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Usu&aacute;rio ou senha incorreto");
            return request.getContextPath(); // chama de volta a página de login
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("exception", e);
            return request.getContextPath() + "/error/error";
        }

        return null;
    }
}
