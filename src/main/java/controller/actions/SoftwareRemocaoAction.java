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

import dao.DAOFactory;
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
import model.Software;
import util.Logger;

public class SoftwareRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Software software = new Software();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            DAOFactory dao = DAOFactory.getFactory();

            software.setId(Integer.parseInt(request.getParameter("id")));
            dao.getSoftwareDAO().delete(software);

        } catch (Exception e) {
            Logger.logSevere(e, SoftwareRemocaoAction.class);
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + " (" + u.getUsername() + ") tentou "
                    + "remover o software " + software.getFabricante() + " " + software.getNome() + " (#" + software.getId() + ") do banco de dados.");

            session.setAttribute("msg", "Erro ao remover o software");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/software/lista";
        }
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") acaba de "
                + "remover o software " + software.getFabricante() + " " + software.getNome() + " (#" + software.getId() + ") do banco de dados.");

        session.setAttribute("msg", "Software removido");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/software/lista";
    }
}
