/*
 * Copyright (C) 2018 thales
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
import util.DatabaseConnection;
import util.Logger;
import util.SiGLa;

public class ConfigurationDatabaseAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            String database = request.getParameter("db-name");
            String user = request.getParameter("db-user");
            String passwd = request.getParameter("db-passwd");
            String addr = request.getParameter("db-host");
            String dbms = request.getParameter("db-dbms");

            SiGLa.writeProperty("sigla.db.name", database);
            SiGLa.writeProperty("sigla.db.user", user);
            SiGLa.writeProperty("sigla.db.passwd", passwd);
            SiGLa.writeProperty("sigla.db.addr", addr);
            SiGLa.writeProperty("sigla.db.dbms", dbms);

            DatabaseConnection.checkDatabase();
        } catch (ConnectException e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "O banco de dados recusou os dados");
            session.setAttribute("status", "error");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "alterar informações do banco de dados.");
            return request.getContextPath() + "/admin/database";
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "Erro ao atualizar as informações");
            session.setAttribute("status", "error");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "alterar informações do banco de dados.");
            return request.getContextPath() + "/admin/database";
        }

        session.setAttribute("msg", "Banco de dados atualizado");
        session.setAttribute("status", "success");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") atualizou o banco de dados para "
                + SiGLa.getDbName() + "(SGBD: " + SiGLa.getDbDbms() + ".");
        return request.getContextPath() + "/admin/database";
    }
}
