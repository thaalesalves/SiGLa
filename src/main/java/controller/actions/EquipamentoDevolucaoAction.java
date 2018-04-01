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
import model.Equipamento;
import model.Pessoa;
import util.Logger;

public class EquipamentoDevolucaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Equipamento e = new Equipamento();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            e.setId(Integer.parseInt(request.getParameter("equip-id")));
            fac.getEquipamentoDAO().devolver(e);
        } catch (Exception ex) {
            Logger.logSevere(ex, EquipamentoInsercaoAction.class);
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao devolver computador");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "devolver o equipamento " + e.getNome() + "(#" + e.getId() + ").");
            return request.getContextPath() + "/equip/lista";
        }

        session.setAttribute("status", "success");
        session.setAttribute("msg", "Computador devolvido");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") acaba de fazer a devolução de " + e.getNome() + "(#" + e.getId() + ").");
        return request.getContextPath() + "/equip/lista";
    }
}
