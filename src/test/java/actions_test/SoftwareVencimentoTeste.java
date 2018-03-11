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
package actions_test;

import dao.DAOFactory;
import java.util.List;
import model.Licenca;
import util.IO;
import util.Json;
import util.Logger;

public class SoftwareVencimentoTeste {

    public static void main(String[] args) {
        try {
            DAOFactory fac = DAOFactory.getFactory();
            List<Licenca> licencas = fac.getLicencaDAO().selectVencimento();
            
            for (Licenca i : licencas) {
                i.setSoftware(fac.getSoftwareDAO().selectId(i.getSoftware()));
            }
            
            IO.writeln(Json.toJson(licencas));
        } catch (Exception e) {
            Logger.logSevere(e, SoftwareLicencaTeste.class);
        }
    }
}
