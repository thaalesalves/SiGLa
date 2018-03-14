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

import dao.sgbd.FornecedorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import util.DatabaseConnection;
import util.Logger;

public class FornecedorDAOPsql implements FornecedorDAO {

    @Override
    public void insert(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_fornecedor VALUES(DEFAULT, ?, ?, ?)");
            pstmt.setString(1, fornecedor.getNome());
            pstmt.setString(2, fornecedor.getTelefone());
            pstmt.setString(3, fornecedor.getEmail());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, FornecedorDAOPsql.class);
        }
    }

    @Override
    public Fornecedor select(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_fornecedor WHERE id = ?");
            pstmt.setInt(1, fornecedor.getId());
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
            }
            
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, FornecedorDAOPsql.class);
        }
        
        return fornecedor;
    }

    @Override
    public List<Fornecedor> select() throws SQLException, ClassNotFoundException {
        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_fornecedor");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedores.add(fornecedor);
            }
            
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, FornecedorDAOPsql.class);
        }
        
        return fornecedores;
    }
}
