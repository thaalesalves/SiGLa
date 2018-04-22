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
package dao.sgbd.psql;

import dao.sgbd.IncidenteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Equipamento;
import model.Incidente;
import util.DatabaseConnection;
import util.IO;
import util.Logger;

public class IncidenteDAOPsql extends IncidenteDAO {

    @Override
    public List<Incidente> select() throws SQLException, ClassNotFoundException {
        List<Incidente> incidentes = new ArrayList<Incidente>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_incidente");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Incidente incidente = new Incidente();
                incidente.setId(rs.getInt("id"));
                incidente.setDataRetirada(IO.getData(rs.getString("data_retirada")));
                incidente.setDataDevolucao(IO.getData(rs.getString("data_devolucao")));
                incidente.setDescricao(rs.getString("motivo"));
                incidente.setEquipamento(new Equipamento());
                incidente.getEquipamento().setId(rs.getInt("equipamento"));
                incidentes.add(incidente);
            }
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, IncidenteDAOPsql.class);
        }
        
        return incidentes;
    }

    @Override
    public Incidente select(Incidente incidente) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_incidente WHERE id = ?");
            pstmt.setInt(1, incidente.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                incidente.setDataDevolucao(IO.getData(rs.getString("data_devolucao")));
                incidente.setDataRetirada(IO.getData(rs.getString("data_retirada")));
                incidente.setDescricao(rs.getString("motivo"));
                incidente.setEquipamento(new Equipamento());
                incidente.getEquipamento().setId(rs.getInt("equipamento"));
            }
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, IncidenteDAOPsql.class);
        }
        
        return incidente;
    }

    @Override
    public void insert(Incidente incidente) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_incidente VALUES(DEFAULT, ?, ?, ?)");
            pstmt.setString(1, incidente.getDescricao());
            pstmt.setInt(2, incidente.getEquipamento().getId());
            pstmt.setString(3, IO.formatData(incidente.getDataRetirada()));
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, IncidenteDAOPsql.class);
        }
    }

    @Override
    public void devolver(Incidente incidente) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tb_incidente SET data_devolucao = ?, resolucao = ? WHERE id = ?");
            pstmt.setString(1, IO.formatData(incidente.getDataDevolucao()));
            pstmt.setString(2, incidente.getResolucao());
            pstmt.setInt(3, incidente.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            Logger.logSevere(e, IncidenteDAOPsql.class);
        }
    }

    @Override
    public void update(Incidente incidente) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tb_incidente SET motivo = ? WHERE id = ?");
            pstmt.setString(1, incidente.getDescricao());
            pstmt.setInt(2, incidente.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            Logger.logSevere(e, IncidenteDAOPsql.class);
        }
    }
}
