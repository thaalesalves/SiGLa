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
package dao_test;

import controller.json.LaboratoriosDisponiveisJson;
import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import model.Laboratorio;
import model.Modulo;
import model.Reserva;
import model.Software;
import util.IO;

public class LaboratoriosDisponiveisTeste {

    public static void main(String[] args) throws SQLException, NullPointerException, ClassNotFoundException, NamingException, IOException {
        Reserva r = new Reserva();
        DAOFactory fac = DAOFactory.getFactory();
        ArrayList<Laboratorio> al = new ArrayList<Laboratorio>();

        try {
            String[] modulos = {"1", "2", "3"};
            String[] softwares = {"1"};

            for (String i : modulos) {
                Modulo mod = new Modulo();
                mod.setId(Integer.parseInt(i));
                r.getModulos().add(mod);
            }

            r.setDiaDaSemana("Segunda-feira");

            for (String i : softwares) {
                Software sw = new Software();
                sw.setId(Integer.parseInt(i));
                r.getSoftwares().add(sw);
            }
            
            al = fac.getLaboratorioDAO().selectAvailableLabs(r);
        } catch (Exception e) {
            util.Logger.logSevere(e, LaboratoriosDisponiveisJson.class);
        }

        IO.writeln(util.Json.toJson(al));
    }
}
