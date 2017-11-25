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
package dao.dao.psql;

import dao.dao.IncidenteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Equipamento;
import model.Incidente;
import model.IncidenteInformacao;
import model.Pessoa;
import util.DatabaseConnection;

/**
 *
 * @author thaalesalves
 */
public class IncidenteDAOPsql extends IncidenteDAO {

    @Override
    public List<Incidente> select() throws SQLException, ClassNotFoundException {
        List<Incidente> incidentes = new ArrayList<Incidente>();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT i.usuario AS user, i.id AS incidente, i.data_abertura AS data_abertura, i.descricao AS descricao, eq.id AS equip_id, eq.nome AS computador, lab.numero AS laboratorio FROM tb_laboratorio lab, tb_incidente i, tb_equipamento eq WHERE eq.laboratorio = lab.id AND i.equipamento = eq.id");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Incidente incidente = new Incidente();
                incidente.setEquipamento(new Equipamento());
                incidente.setPessoa(new Pessoa());
                incidente.getEquipamento().setId(rs.getInt("equip_id"));
                incidente.getEquipamento().setNome(rs.getString("computador"));
                incidente.getPessoa().setUsername(rs.getString("user"));
                incidente.setId(rs.getInt("incidente"));
                incidente.setDataAbertura(rs.getString("data_abertura"));
                incidente.setDescricao(rs.getString("descricao"));
                
                incidentes.add(incidente);
            }

            conn.close();
        }

        return incidentes;
    }

    @Override
    public Incidente select(Incidente incidente) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT i.usuario AS usuario, i.id AS incidente, i.data_abertura AS data_abertura, i.descricao AS descricao, eq.id AS equip_id, eq.nome AS computador, lab.numero AS laboratorio "
                    + "FROM tb_laboratorio lab, tb_incidente i, tb_equipamento eq WHERE eq.laboratorio = lab.id AND i.equipamento = eq.id AND i.id = ?");
            pstmt.setInt(1, incidente.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                incidente.setEquipamento(new Equipamento());
                incidente.setPessoa(new Pessoa());
                incidente.getEquipamento().setId(rs.getInt("equip_id"));
                incidente.getEquipamento().setNome(rs.getString("computador"));
                incidente.getPessoa().setUsername(rs.getString("usuario"));
                incidente.setId(rs.getInt("incidente"));
                incidente.setDataAbertura(rs.getString("data_abertura"));
                incidente.setDescricao(rs.getString("descricao"));
            }

            conn.close();
        }

        return incidente;
    }

    @Override
    public void adicionaInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_info_incidente VALUES(DEFAULT, ?, ?, ?, ?)");
            pstmt.setInt(1, info.getIncidente().getId());
            pstmt.setString(2, info.getDescricao());
            pstmt.setString(3, info.getDataAdicao());
            pstmt.setString(4, info.getPessoa().getUsername());
            pstmt.executeUpdate();
            conn.close();
        }
    }

    @Override
    public void removeInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editaInformacao(IncidenteInformacao info) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
