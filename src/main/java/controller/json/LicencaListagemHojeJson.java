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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Erro;
import model.Licenca;
import model.Pessoa;
import util.Logger;

public class LicencaListagemHojeJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        List<Licenca> licencas = new ArrayList<Licenca>();
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            licencas = fac.getLicencaDAO().selectVencimento();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaListagemHojeJson.class);
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou listar as licenças que vencem hoje.");
            Erro err = new Erro();
            err.setErro(e.getMessage());
            return util.Json.toJson(err);
        }
        Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") listou as licenças que vencem hoje.");
        return util.Json.toJson(licencas);
    }
}
