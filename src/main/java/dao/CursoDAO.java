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
*   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
**/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Curso;
import util.DatabaseConnection;

public class CursoDAO {

    private final String SELECT_ALL = "SELECT * FROM curso";
    private final String SELECT_ID = "SELECT nome, modalidade FROM curso WHERE id = ?";
    private final String DELETE = "DELETE FROM curso WHERE id = ?";
    private final String INSERT = "INSERT INTO curso VALUES(NEXTVAL('seq_curso'), ?, ?)";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectAll()">
    public ArrayList<Curso> selectAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Curso> ac = new ArrayList<Curso>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setModalidade(rs.getString("modalidade"));

                ac.add(c);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return ac;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectId(Curso)">
    public Curso selectId(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ID);
            pstmt.setInt(1, c.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                c.setNome(rs.getString("nome"));
                c.setModalidade(rs.getString("modalidade"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return c;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: insert(Curso)">
    public void insert(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT);

            pstmt.setString(1, c.getNome());
            pstmt.setString(2, c.getModalidade());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: delete(Curso)">
    public void delete(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(DELETE);

            pstmt.setInt(1, c.getId());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }//</editor-fold>
}