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
package controller.json;

import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Erro;
import model.Laboratorio;
import model.Pessoa;
import util.Logger;

public class LaboratorioIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        DAOFactory fac = DAOFactory.getFactory();
        Laboratorio lab = new Laboratorio();
        lab.setId(Integer.parseInt(request.getParameter("id")));
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");

        if (lab.getId() < 1) {
            Erro err = new Erro();
            err.setErro("Tentativa ilegal de passar valores.");
            Logger.logOutput("Parece que " + u.getNomeCompleto() + "(" + u.getUsername() + ") passou valores inválidos ao buscar um laboratório. ID: " + lab.getId());
            return util.Json.toJson(err);
        }
        Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") buscou detalhes do laboratório #" + lab.getId());
        return util.Json.toJson(fac.getLaboratorioDAO().selectLaboratorio(lab));
    }
}
