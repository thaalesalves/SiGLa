/*
 * Copyright (C) 2016 Thales Alves Pereira
 *
 * This file is part of SiGla.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGla.  If not, see <http://www.gnu.org/licenses/>.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pessoa;
import util.DatabaseConnection;

public class PessoaDAO {

    private final String SELECT_EMAIL = "SELECT personal_email FROM pessoa WHERE username = ?";
    private final String INSERT = "INSERT INTO pessoa VALUES(?, ?)";

    public void insert(Pessoa p) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT);

            pstmt.setString(1, p.getUsername());
            pstmt.setString(2, p.getPersonalEmail());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    public Pessoa selectEmail(Pessoa p) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_EMAIL);

            pstmt.setString(1, p.getUsername());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                p.setPersonalEmail(rs.getString("personal_email"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return p;
    }
}
