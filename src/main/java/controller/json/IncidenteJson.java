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
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Erro;
import model.Incidente;
import model.Pessoa;
import util.ActiveDirectory;
import util.Logger;

/**
 *
 * @author thaalesalves
 */
public class IncidenteJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        HttpSession session = request.getSession();
        Incidente incidente = new Incidente();
        incidente.setId(Integer.parseInt(request.getParameter("id")));
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        if (incidente.getId() < 1) {
            Erro err = new Erro();
            err.setErro("Valores inválidos passados");
            Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") passou valores inválidos ao listar um incidente. ID: " + incidente.getId());
            return util.Json.toJson(err);
        }

        DAOFactory fac = DAOFactory.getFactory();
        incidente = fac.getIncidenteDAO().select(incidente);

        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        incidente.getPessoa().setNomeCompleto(ad.getCN(incidente.getPessoa()));
        incidente.getPessoa().setEmail(ad.getMail(incidente.getPessoa()));
        incidente.getPessoa().setNome(ad.getGivenName(incidente.getPessoa()));
        incidente.getPessoa().setEmpresa(ad.getCompany(incidente.getPessoa()));
        incidente.getPessoa().setCargo(ad.getTitle(incidente.getPessoa()));
        incidente.getPessoa().setDepto(ad.getDepartment(incidente.getPessoa()));
        ad.closeLdapConnection();
        Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") buscou detalhes do incidente #" + incidente.getId());
        return util.Json.toJson(incidente);
    }
}
