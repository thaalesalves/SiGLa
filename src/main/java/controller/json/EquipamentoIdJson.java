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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipamento;
import model.Erro;
import model.Incidente;
import model.Pessoa;
import util.Logger;

public class EquipamentoIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        Equipamento e = new Equipamento();
        try {

            DAOFactory fac = DAOFactory.getFactory();
            Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");

            e.setId(Integer.parseInt(request.getParameter("id")));
            e.setIncidentes(new ArrayList<Incidente>());
            e.getIncidentes().add(fac.getIncidenteDAO().selectAberto(e));
            if (e.getId() < 1) {
                Erro err = new Erro();
                err.setErro("Tentativa ilegal de passar valores.");
                Logger.logOutput(p.getNomeCompleto() + "(" + p.getUsername() + ") passou valores ilegais ao listar um equipamento. ID: " + e.getId());
                return util.Json.toJson(err);
            }

            fac.getEquipamentoDAO().select(e);
            Logger.logOutput(p.getNomeCompleto() + "(" + p.getUsername() + ") buscou detalhes do equipamento " + e.getNome() + "(#" + e.getId() + ").");
        } catch (Exception ex) {
            Logger.logSevere(ex, EquipamentoIdJson.class);
        }
        return util.Json.toJson(e);
    }
}
