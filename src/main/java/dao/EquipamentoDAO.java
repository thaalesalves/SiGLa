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
import model.Equipamento;
import model.Laboratorio;
import util.DatabaseConnection;

public class EquipamentoDAO {

    public void insert(Equipamento eq) throws SQLException, ClassNotFoundException {
        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_equipamento VALUES(DEFAULT, ?, ?, ?, ?, ?, 1)");
            
            pstmt.setString(1, eq.getNome());
            pstmt.setInt(2, eq.getLab().getId());
            pstmt.setString(3, eq.getIp());
            pstmt.setString(4, eq.getMac());
            pstmt.setString(5, eq.getConfig());
            
            pstmt.executeUpdate();
            
            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, EquipamentoDAO.class);
        }
    }
    
    public ArrayList<Equipamento> select() throws SQLException, ClassNotFoundException {
        ArrayList<Equipamento> eqs = new ArrayList<Equipamento>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT e.*, l.id AS lab, l.numero FROM tb_equipamento e JOIN tb_laboratorio l ON e.laboratorio = l.id");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Equipamento e = new Equipamento();

                e.setLab(new Laboratorio());
                e.setId(rs.getInt("id"));
                e.setStatus(rs.getInt("status"));                
                e.setNome(rs.getString("nome"));
                e.setIp(rs.getString("ip"));
                e.setMac(rs.getString("mac"));
                e.setConfig(rs.getString("config"));
                
                e.getLab().setId(rs.getInt("lab"));
                e.getLab().setNumero(rs.getString("numero"));

                eqs.add(e);
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, EquipamentoDAO.class);
        }

        return eqs;
    }

    public int qtdEquip() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM tb_equipamento");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("count");
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return qtd;
    }
}
