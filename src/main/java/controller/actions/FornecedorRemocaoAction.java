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
import model.Fornecedor;
import model.Pessoa;
import util.Logger;

public class FornecedorRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Fornecedor fornecedor = new Fornecedor();
        Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            fornecedor.setId(Integer.parseInt(request.getParameter("id")));
            fac.getFornecedorDAO().delete(fornecedor);

        } catch (Exception e) {
            session.setAttribute("msg", "Erro ao remover fornecedor");
            session.setAttribute("status", "error");
            Logger.logOutput("Houve um erro quando " + p.getNomeCompleto() + " (" + p.getUsername() + ") tentou "
                    + "remover o fornecedor #" + fornecedor.getId());
            Logger.logSevere(e, FornecedorRemocaoAction.class);
            return request.getContextPath() + "/fornecedor/lista";
        }

        session.setAttribute("msg", "Fornecedor removido.");
        session.setAttribute("status", "success");
        Logger.logOutput(p.getNomeCompleto() + "(" + p.getUsername() + ") removeu o fornecedor #" + fornecedor.getId());
        return request.getContextPath() + "/fornecedor/lista";
    }
}
