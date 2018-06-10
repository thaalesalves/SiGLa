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
import model.Pessoa;
import model.Software;
import util.Json;
import util.Logger;

public class SoftwareLicencaListagemJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        try {
            DAOFactory fac = DAOFactory.getFactory();
            Software sw = new Software();
            Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");
            sw.setId(Integer.parseInt(request.getParameter("id")));

            if (sw.getId() < 1) {
                Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") passou valores ilegais ao buscar um software. ID: " + sw.getId());
                Erro err = new Erro();
                err.valorIlegal();
                return Json.toJson(sw);
            }

            sw = fac.getSoftwareDAO().selectId(sw);
            sw = fac.getSoftwareDAO().selectLicenca(sw);
            Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") buscou detalhes do software #" + sw.getId());
            return Json.toJson(sw);
        } catch (Exception e) {
            Logger.logSevere(e, SoftwareLicencaListagemJson.class);
        }

        return null;
    }
}
