/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */
package dao.sgbd;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Modulo;
import model.Reserva;
import model.Solicitacao;

public interface ModuloDAO {

    public void insert(Modulo modulo) throws SQLException, ClassNotFoundException, NullPointerException;

    public ArrayList<Modulo> select() throws SQLException, ClassNotFoundException, NullPointerException;

    public ArrayList<Modulo> selectAux(Solicitacao s) throws SQLException, NullPointerException, ClassNotFoundException;

    public ArrayList<Modulo> selectAux(Reserva s) throws SQLException, NullPointerException, ClassNotFoundException;
}
