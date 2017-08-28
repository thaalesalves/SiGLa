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
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Reserva;
import model.Software;
import model.Solicitacao;
import util.DatabaseConnection;

public class SoftwareDAO {

    public ArrayList<Software> selectSoftwareAux(Solicitacao solicitacao) throws SQLException, ClassNotFoundException, NullPointerException {
        ArrayList<Software> arrayRes = new ArrayList<Software>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from tb_software s JOIN aux_sw_soli ss ON s.id = ss.sw where ss.res = ?");
            pstmt.setInt(1, solicitacao.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Software s = new Software();

                s.setId(rs.getInt("id"));
                s.setFabricante(rs.getString("fabricante"));
                s.setNome(rs.getString("nome"));

                arrayRes.add(s);
            }

            conn.close();
        }

        return arrayRes;
    }

    public ArrayList<Software> selectSoftwareAux(Reserva r) throws SQLException, ClassNotFoundException, NullPointerException {
        ArrayList<Software> arrayRes = new ArrayList<Software>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_software s JOIN aux_sw_res ss ON s.id = ss.sw where ss.res = ?");
            pstmt.setInt(1, r.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Software s = new Software();

                s.setId(rs.getInt("id"));
                s.setFabricante(rs.getString("fabricante"));
                s.setNome(rs.getString("nome"));

                arrayRes.add(s);
            }

            conn.close();
        }

        return arrayRes;
    }

    public void insertSoftware(Software s) throws SQLException, ClassNotFoundException, NullPointerException {
        try (Connection connString = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("INSERT INTO tb_software VALUES(DEFAULT, ?, ?)");

            pstmt.setString(2, s.getFabricante());
            pstmt.setString(1, s.getNome());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    public ArrayList<Software> selectAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Software> sws = new ArrayList<Software>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT id, nome, fabricante FROM tb_software");
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
            util.Logger.logSevere(e, this.getClass());
        }

        return sws;
    }

    public Software selectId(Software s) throws SQLException, NullPointerException, ClassNotFoundException {

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT fabricante, nome FROM tb_software WHERE id = ?");
            pstmt.setInt(1, s.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setNome(rs.getString("nome"));
                s.setFabricante(rs.getString("fabricante"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return s;
    }
}
