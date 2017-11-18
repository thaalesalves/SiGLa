/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package dao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Equipamento;

public interface EquipamentoDAO {

    public void insert(Equipamento eq) throws SQLException, ClassNotFoundException;

    public ArrayList<Equipamento> select() throws SQLException, ClassNotFoundException;

    public int qtdEquip() throws SQLException, ClassNotFoundException;
    
    public Equipamento select(Equipamento equipamento) throws SQLException, ClassNotFoundException;
    
    public void retirar(Equipamento e) throws SQLException, ClassNotFoundException;
    
    public void devolver(Equipamento e) throws SQLException, ClassNotFoundException;
}
