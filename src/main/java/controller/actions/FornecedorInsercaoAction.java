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

public class FornecedorInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");

        try {
            Fornecedor fornecedor = new Fornecedor();
            DAOFactory fac = DAOFactory.getFactory();
            fornecedor.setEmail(request.getParameter("email"));
            fornecedor.setNome(request.getParameter("nome"));
            fornecedor.setTelefone(request.getParameter("telefone"));
            fac.getFornecedorDAO().insert(fornecedor);
        } catch (Exception e) {
            Logger.logSevere(e, FornecedorInsercaoAction.class);
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao cadastrar computador");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "inserir um novo fornecedor");
            return request.getContextPath() + "/fornecedor/novo";
        }

        session.setAttribute("status", "success");
        session.setAttribute("msg", "Fornecedor cadastrado com sucesso");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") acaba de inserir um fornecedor.");
        return request.getContextPath() + "/fornecedor/novo";
    }

}
