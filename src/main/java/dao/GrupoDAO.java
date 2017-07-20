/*
 * Copyright (C) 2016 Thales Alves Pereira
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

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Grupo;
import util.DatabaseConnection;

public class GrupoDAO {
    
    private final String SELECT = "SELECT id, grupo, cargo FROM grupo";
    
    public ArrayList<Grupo> select() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Grupo> arrayg = new ArrayList<Grupo>();
        
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Grupo g = new Grupo();
                
                g.setId(rs.getInt("id"));
                g.setGrupo(rs.getString("grupo"));
                g.setRole(rs.getString("cargo"));
                
                arrayg.add(g);
            }
            
            connString.close();
        }
        
        return arrayg;
    }
}
