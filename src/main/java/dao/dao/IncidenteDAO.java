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
package dao.dao;

import java.sql.SQLException;
import java.util.List;
import model.Incidente;
import model.IncidenteInformacao;

/**
 *
 * @author thaalesalves
 */
public abstract class IncidenteDAO {
    public abstract List<Incidente> select() throws SQLException, ClassNotFoundException;
    public abstract Incidente select(Incidente incidente) throws SQLException, ClassNotFoundException;
    public abstract void adicionaInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException;
    public abstract void removeInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException;
    public abstract void editaInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException;
    
}
