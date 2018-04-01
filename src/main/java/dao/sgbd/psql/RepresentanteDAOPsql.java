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
package dao.sgbd.psql;

import dao.sgbd.RepresentanteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.Representante;
import util.DatabaseConnection;
import util.Logger;

public class RepresentanteDAOPsql implements RepresentanteDAO {

    @Override
    public void insert(Representante representante) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_representante VALUES(DEFAULT, ?, ?, ?, ?)");
            pstmt.setString(1, representante.getNome());
            pstmt.setString(2, representante.getTelefone());
            pstmt.setString(3, representante.getEmail());
            pstmt.setInt(4, representante.getFornecedor().getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, RepresentanteDAOPsql.class);
        }
    }

    @Override
    public Representante select(Representante representante) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_representante WHERE id = ?");
            pstmt.setInt(1, representante.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                representante.setId(rs.getInt("id"));
                representante.setNome(rs.getString("nome"));
                representante.setTelefone(rs.getString("telefone"));
                representante.setEmail(rs.getString("email"));
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, RepresentanteDAOPsql.class);
        }

        return representante;
    }

    @Override
    public List<Representante> select() throws SQLException, ClassNotFoundException {
        List<Representante> representantes = new ArrayList<Representante>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_representante");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Representante representante = new Representante();
                representante.setId(rs.getInt("id"));
                representante.setNome(rs.getString("nome"));
                representante.setTelefone(rs.getString("telefone"));
                representante.setEmail(rs.getString("email"));
                representantes.add(representante);
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, RepresentanteDAOPsql.class);
        }

        return representantes;
    }

    @Override
    public List<Representante> select(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        List<Representante> representantes = new ArrayList<Representante>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_representante WHERE fornecedor = ?");
            pstmt.setInt(1, fornecedor.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Representante representante = new Representante();
                representante.setId(rs.getInt("id"));
                representante.setNome(rs.getString("nome"));
                representante.setTelefone(rs.getString("telefone"));
                representante.setEmail(rs.getString("email"));
                representantes.add(representante);
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, RepresentanteDAOPsql.class);
        }

        return representantes;
    }
}
