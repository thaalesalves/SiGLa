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
package dao.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Laboratorio;
import model.Reserva;
import model.Modulo;
import util.DatabaseConnection;

public class LaboratorioDAOMysql implements dao.dao.LaboratorioDAO {

    @Override
    public ArrayList<Laboratorio> selectReservedLabs(Reserva reserva) throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Laboratorio> arrayLab = new ArrayList<Laboratorio>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT l.* "
                    + "FROM tb_laboratorio l, tb_reserva r, tb_modulo m, aux_modulo_res mr "
                    + "WHERE r.dia_semana = ? "
                    + "AND l.id = r.laboratorio "
                    + "AND mr.res = r.id "
                    + "AND mr.modulo = m.id "
                    + "AND m.id = ?");

            for (Modulo i : reserva.getModulos()) {
                pstmt.setString(1, reserva.getDiaDaSemana());
                pstmt.setInt(2, i.getId());

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Laboratorio l = new Laboratorio();

                    l.setId(rs.getInt("id"));
                    l.setNumero(rs.getString("numero"));
                    l.setComputadores(rs.getInt("qtd_comps"));
                    l.setCapacidade(rs.getInt("qtd_alunos"));

                    arrayLab.add(l);
                }
            }

            conn.close();
        }

        return arrayLab;
    }

    @Override
    public Laboratorio selectLaboratorio(Laboratorio l) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT numero FROM tb_laboratorio WHERE id = ?");

            pstmt.setInt(1, l.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                l.setNumero(rs.getString("numero"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return l;
    }

    @Override
    public void insertLaboratorio(Laboratorio l) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("INSERT INTO tb_laboratorio VALUES(DEFAULT, ?, ?, ?)");

            pstmt.setString(1, l.getNumero());
            pstmt.setInt(2, l.getComputadores());
            pstmt.setInt(3, l.getCapacidade());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    @Override
    public ArrayList<Laboratorio> selectLaboratorios() throws SQLException, ClassNotFoundException {
        ArrayList<Laboratorio> laboratorios = new ArrayList<Laboratorio>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM tb_laboratorio");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Laboratorio l = new Laboratorio();
                l.setId(rs.getInt("id"));
                l.setNumero(rs.getString("numero"));
                l.setCapacidade(rs.getInt("qtd_alunos"));
                l.setComputadores(rs.getInt("qtd_comps"));

                laboratorios.add(l);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return laboratorios;
    }

    @Override
    public int qtdLabs() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) AS count FROM tb_laboratorio");
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

    @Override
    public ArrayList<Laboratorio> selectAvailableLabs(Reserva reserva) throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Laboratorio> arrayLab = this.selectLaboratorios();
        ArrayList<Laboratorio> labsReservados = this.selectReservedLabs(reserva);
        arrayLab.removeAll(labsReservados);

        return arrayLab;
    }
}
