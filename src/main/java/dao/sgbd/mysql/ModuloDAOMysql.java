/*
 * Copyright (C) 2017 Thales Alves Pereira
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
package dao.sgbd.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Modulo;
import model.Reserva;
import model.Solicitacao;

public class ModuloDAOMysql implements dao.sgbd.ModuloDAO {

    @Override
    public void insert(Modulo modulo) throws SQLException, ClassNotFoundException, NullPointerException {
        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_modulo VALUES(?)");

            pstmt.setInt(1, modulo.getId());

            pstmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    @Override
    public ArrayList<Modulo> select() throws SQLException, ClassNotFoundException, NullPointerException {
        ArrayList<Modulo> ms = new ArrayList<Modulo>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_modulo");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Modulo m = new Modulo();

                ms.add(m);
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return ms;
    }

    @Override
    public ArrayList<Modulo> selectAux(Solicitacao s) throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Modulo> ms = new ArrayList<Modulo>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM aux_modulo_soli WHERE res = ?");
            pstmt.setInt(1, s.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Modulo m = new Modulo();
                m.setId(rs.getInt("modulo"));

                ms.add(m);
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return ms;
    }

    @Override
    public ArrayList<Modulo> selectAux(Reserva s) throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Modulo> ms = new ArrayList<Modulo>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM aux_modulo_res WHERE res = ?");
            pstmt.setInt(1, s.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Modulo m = new Modulo();
                m.setId(rs.getInt("modulo"));

                ms.add(m);
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return ms;
    }
}
