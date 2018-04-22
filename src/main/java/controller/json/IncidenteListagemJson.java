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
import model.Pessoa;
import util.Logger;

/**
 *
 * @author thaalesalves
 */
public class IncidenteListagemJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        HttpSession session = request.getSession();
        DAOFactory fac = DAOFactory.getFactory();
        List<Incidente> incidentes = fac.getIncidenteDAO().select();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") listou os incidentes.");
        return util.Json.toJson(incidentes);
    }
}
