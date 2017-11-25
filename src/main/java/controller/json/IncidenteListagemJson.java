/*
 * Copyright (C) 2017 thaalesalves
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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Incidente;
import util.ActiveDirectory;

/**
 *
 * @author thaalesalves
 */
public class IncidenteListagemJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        HttpSession session = request.getSession();
        DAOFactory fac = DAOFactory.getFactory();
        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        List<Incidente> incidentes = fac.getIncidenteDAO().select();

        for (Incidente i : incidentes) {
            i.getPessoa().setNomeCompleto(ad.getCN(i.getPessoa()));
            i.getPessoa().setEmail(ad.getMail(i.getPessoa()));
            i.getPessoa().setNome(ad.getGivenName(i.getPessoa()));
            i.getPessoa().setEmpresa(ad.getCompany(i.getPessoa()));
            i.getPessoa().setCargo(ad.getTitle(i.getPessoa()));
            i.getPessoa().setDepto(ad.getDepartment(i.getPessoa()));
        }

        return util.Json.toJson(incidentes);
    }
}
