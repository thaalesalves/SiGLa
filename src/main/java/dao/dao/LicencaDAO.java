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
package dao.dao;

import java.sql.SQLException;
import java.util.List;
import model.Licenca;
import model.Software;

public interface LicencaDAO {
    public Licenca select(Software software) throws SQLException, ClassNotFoundException;
    public Licenca select(Licenca licenca) throws SQLException, ClassNotFoundException;
    public List<Licenca> select() throws SQLException, ClassNotFoundException;
    public List<Licenca> selectVencimento() throws SQLException, ClassNotFoundException;
    public List<Licenca> selectVencimento(Licenca licenca) throws SQLException, ClassNotFoundException;
    public void ativa(Licenca licenca) throws SQLException, ClassNotFoundException;
    public void desativa(Licenca licenca) throws SQLException, ClassNotFoundException;
}
