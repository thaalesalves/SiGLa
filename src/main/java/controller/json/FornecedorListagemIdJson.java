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
package controller.json;

import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Erro;
import model.Fornecedor;
import model.Pessoa;
import util.Logger;

public class FornecedorListagemIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        Fornecedor fornecedor = new Fornecedor();
        DAOFactory fac = DAOFactory.getFactory();
        fornecedor.setId(Integer.parseInt(request.getParameter("id")));
        Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");

        if (fornecedor.getId() < 1) {
            Erro err = new Erro();
            err.valorIlegal();
            Logger.logOutput(p.getNomeCompleto() + "(" + p.getUsername() + ") passou valores ilegais ao buscar um fornecedor. ID: " + fornecedor.getId());
            return util.Json.toJson(err);
        }

        fornecedor = fac.getFornecedorDAO().select(fornecedor);
        Logger.logOutput(p.getNomeCompleto() + " (" + p.getUsername() + ") solicitou detalhes do fornecedor #" + fornecedor.getId());

        return util.Json.toJson(fornecedor);
    }

}
