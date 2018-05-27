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
package dao.sgbd;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Laboratorio;
import model.Reserva;
import model.Software;
import model.Solicitacao;

public interface SoftwareDAO {

    public int countSoftwares() throws SQLException, ClassNotFoundException, NullPointerException;

    public ArrayList<Software> selectSoftwareAux(Solicitacao solicitacao) throws SQLException, ClassNotFoundException, NullPointerException;

    public ArrayList<Software> selectSoftwareAux(Reserva r) throws SQLException, ClassNotFoundException, NullPointerException;

    public void insertSoftware(Software s) throws SQLException, ClassNotFoundException, NullPointerException;

    public ArrayList<Software> selectAll() throws SQLException, NullPointerException, ClassNotFoundException;

    public Software selectId(Software s) throws SQLException, NullPointerException, ClassNotFoundException;

    public ArrayList<Software> selectSoftwareAux(Laboratorio lab) throws SQLException, ClassNotFoundException;

    public Software selectLicenca(Software sw) throws SQLException, ClassNotFoundException;
    
    public void delete(Software sw) throws SQLException, ClassNotFoundException;
    
    public ArrayList<Software> selectAllActive() throws SQLException, NullPointerException, ClassNotFoundException;
}
