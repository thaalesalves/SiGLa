/*
 * Copyright (C) 2017 thaal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.actions;

import dao.DAOFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Grupo;
import model.Pessoa;
import util.Logger;
import util.SiGLa;

public class ConfigurationAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        String op = request.getParameter("op");
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            /* Dados de Email */
            String mailName = request.getParameter("sys-name");
            String mailSys = request.getParameter("sys-email");
            String mailGroup = request.getParameter("group-email");
            String mailSystemPasswd = request.getParameter("sys-passwd");
            String mailSmtp = request.getParameter("smtp");

            /* Dados do Banco */
            String dbDbms = request.getParameter("db-dbms");
            String dbName = request.getParameter("db-name");
            String dbHost = request.getParameter("db-host");
            String dbUser = request.getParameter("db-user");
            String dbPasswd = request.getParameter("db-passwd");

            /* Dados do Domínio */
            String port = "";
            String adAuth = request.getParameter("ad-auth");
            String adDomain = request.getParameter("ad-domain");
            String adNetbios = request.getParameter("ad-netbios");
            String adController = request.getParameter("ad-controller");

            if (adAuth.equals("ldaps")) {
                port = "636";
            } else if (adAuth.equals("ldap")) {
                port = "389";
            }

            /* Processando Propriedades */
            SiGLa.writeProperty("sigla.db.name", dbName);
            SiGLa.writeProperty("sigla.db.user", dbUser);
            SiGLa.writeProperty("sigla.db.passwd", dbPasswd);
            SiGLa.writeProperty("sigla.db.addr", dbHost);
            SiGLa.writeProperty("sigla.db.dbms", dbDbms);

            SiGLa.writeProperty("sigla.auth.port", port);
            SiGLa.writeProperty("sigla.auth.domain", adDomain);
            SiGLa.writeProperty("sigla.auth.netbios", adNetbios);
            SiGLa.writeProperty("sigla.auth.method", adAuth);
            SiGLa.writeProperty("sigla.auth.host", adController);

            SiGLa.writeProperty("sigla.mail.name", mailName);
            SiGLa.writeProperty("sigla.mail.system", mailSys);
            SiGLa.writeProperty("sigla.mail.group", mailGroup);
            SiGLa.writeProperty("sigla.mail.system.passwd", mailSystemPasswd);
            SiGLa.writeProperty("sigla.mail.smtp", mailSmtp);

            try {
                Grupo g;
                DAOFactory fac = DAOFactory.getFactory();
                ArrayList<Grupo> ag = new ArrayList<Grupo>();

                /* Dados de Acesso */
                g = new Grupo();
                g.setRole("admin");
                g.setGrupo(request.getParameter("ldap-admin"));
                ag.add(g);

                g = new Grupo();
                g.setRole("funcionario");
                g.setGrupo(request.getParameter("ldap-func"));
                ag.add(g);

                g = new Grupo();
                g.setRole("estagiario");
                g.setGrupo(request.getParameter("ldap-est"));
                ag.add(g);

                g = new Grupo();
                g.setRole("coordenador");
                g.setGrupo(request.getParameter("ldap-coord"));
                ag.add(g);

                g = new Grupo();
                g.setRole("professor");
                g.setGrupo(request.getParameter("ldap-prof"));
                ag.add(g);

                if (!util.DatabaseConnection.checkDatabase()) {
                    for (Grupo i : ag) {
                        fac.getGrupoDAO().insert(i);
                    }
                }
            } catch (Exception e) {
                session.setAttribute("msg", "Erro ao ");
                session.setAttribute("status", "error");
                return request.getContextPath() + "/admin/install";
            }

            session.setAttribute("msg", "SiGLa configurado");
            session.setAttribute("status", "success");

            Logger.logOutput("Parabéns! O SiGLa foi instalado.");

            return request.getContextPath() + "/pagina/login";
        } catch (Exception e) {
            SiGLa.writeProperty("sigla.auth.domain", "null");
            util.Logger.logSevere(e, ConfigurationAction.class);

            session.setAttribute("msg", "Erro ao instalar o SiGLa");
            session.setAttribute("status", "error");
            Logger.logOutput("Houve um erro quando ao instalar o SiGLa");
            return request.getContextPath() + "/admin/install";
        }
    }
}
