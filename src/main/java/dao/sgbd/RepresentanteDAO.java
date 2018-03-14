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
package dao.sgbd;

import java.sql.SQLException;
import java.util.List;
import model.Representante;

/**
 *
 * @author thales
 */
public interface RepresentanteDAO {
    public void insert(Representante representante) throws SQLException, ClassNotFoundException;
    public Representante select(Representante representante) throws SQLException, ClassNotFoundException;
    public List<Representante> select() throws SQLException, ClassNotFoundException;
}
