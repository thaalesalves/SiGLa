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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Laboratorio;
import model.Pessoa;
import util.Logger;

public class LaboratorioRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Laboratorio lab = new Laboratorio();
        try {
            DAOFactory fac = DAOFactory.getFactory();
            lab.setId(Integer.parseInt(request.getParameter("id")));
            if (fac.getLaboratorioDAO().delete(lab) == 1) {
                session.setAttribute("msg", "Laboratório removido com sucesso");
                session.setAttribute("status", "success");

                return request.getContextPath() + "/laboratorio/lista";
            }
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());

            session.setAttribute("msg", "Erro ao remover o laboratório");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/laboratorio/lista";
        }
        session.setAttribute("msg", "Não foi possível remover o laboratório pois existem reservas atreladas a ele");
        session.setAttribute("status", "info");
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") removeu o laboratório #" + lab.getId() + ".");
        return request.getContextPath() + "/laboratorio/lista";
    }
}
