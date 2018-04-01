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
import model.Erro;
import model.Licenca;
import model.Pessoa;
import util.Logger;

public class LicencaListagemIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
        Licenca licenca = new Licenca();

        try {
            DAOFactory fac = DAOFactory.getFactory();
            licenca.setId(Integer.parseInt(request.getParameter("id")));

            if (licenca.getId() < 1) {
                Erro err = new Erro();
                err.setErro("Tentativa ilegal de passar valores.");
                return util.Json.toJson(err);
            }

            licenca = fac.getLicencaDAO().select(licenca);
            licenca.setCodigos(fac.getLicencaCodigoDAO().select(licenca));
            licenca.setSoftware(fac.getSoftwareDAO().selectId(licenca.getSoftware()));
        } catch (Exception e) {
            Logger.logSevere(e, LicencaListagemIdJson.class);
        }

        Logger.logOutput(p.getNomeCompleto() + " (" + p.getUsername() + ") solicitou uma listagem da licença #" + request.getParameter("id") + ".");

        return util.Json.toJson(licenca);
    }
}
