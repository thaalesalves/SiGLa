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
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Laboratorio;
import model.Modulo;
import model.Reserva;

public class LaboratoriosDisponiveisJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NullPointerException, ClassNotFoundException, NamingException, IOException {
        Reserva r = new Reserva();
        DAOFactory fac = DAOFactory.getFactory();
        ArrayList<Laboratorio> al = new ArrayList<Laboratorio>();

        try {
            String[] modulos = request.getParameter("modulo").split(",");

            for (String i : modulos) {
                Modulo mod = new Modulo();
                mod.setId(Integer.parseInt(i));
                r.getModulos().add(mod);
            }

            r.setDiaDaSemana(request.getParameter("dia").replace(" %C3%A7", "ç").replace("%C3%A1", "á"));

            al = fac.getLaboratorioDAO().selectAvailableLabs(r);

            System.out.println("OBJETO: " + al);
        } catch (Exception e) {
            util.Logger.logSevere(e, LaboratoriosDisponiveisJson.class);
        }

        return util.Json.toJson(al);
    }
}
