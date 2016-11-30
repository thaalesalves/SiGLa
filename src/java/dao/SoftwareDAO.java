/**
* Copyright (C) 2016 Thales Alves Pereira
* 
*  This file is part of SiGla.

*   SiGla is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.

*   SiGla is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.

*   You should have received a copy of the GNU General Public License
*   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
**/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Software;
import util.DatabaseConnection;

public class SoftwareDAO {

    private final String SELECT_ALL = "SELECT id, nome, fabricante FROM software;";
    private final String SELECT_ID = "SELECT fabricante, nome FROM software WHERE id = ?";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectAll()">
    public ArrayList<Software> selectAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Software> sws = new ArrayList<Software>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Software sw = new Software();

                sw.setId(rs.getInt("id"));
                sw.setNome(rs.getString("nome"));
                sw.setFabricante(rs.getString("fabricante"));

                sws.add(sw);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }

        return sws;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectId(Software)">
    public Software selectId(Software s) throws SQLException, NullPointerException, ClassNotFoundException {

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ID);
            pstmt.setInt(1, s.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setNome(rs.getString("nome"));
                s.setFabricante(rs.getString("fabricante"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }

        return s;
    }//</editor-fold>
}
