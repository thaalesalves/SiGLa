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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import util.Logger;
import util.SiGLa;

public class ActiveDirectoryAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            String port = "";
            String dominio = request.getParameter("dominio");
            String netbios = request.getParameter("netbios");
            String addr = request.getParameter("host");
            String auth = request.getParameter("auth");

            if (auth.equals("ldaps")) {
                port = "636";
            } else if (auth.equals("ldap")) {
                port = "389";
            }

            SiGLa.writeProperty("sigla.auth.port", port);
            SiGLa.writeProperty("sigla.auth.domain", dominio);
            SiGLa.writeProperty("sigla.auth.netbios", netbios);
            SiGLa.writeProperty("sigla.auth.method", auth);
            SiGLa.writeProperty("sigla.auth.host", addr);
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "Erro ao atualizar domínio");
            session.setAttribute("status", "error");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "alterar informações do domínio.");

            return request.getContextPath();
        }

        session.setAttribute("msg", "Domínio atualizado. Faça login novamente.");
        session.setAttribute("status", "success");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") atualizou o domínio do Active Directory para "
                + SiGLa.getDomain() + ".");
        request.getSession().invalidate();
        return request.getContextPath();
    }
}
