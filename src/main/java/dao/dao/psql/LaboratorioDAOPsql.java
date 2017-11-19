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
package dao.dao.psql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Laboratorio;
import model.Reserva;
import model.Modulo;
import model.Software;
import util.DatabaseConnection;
import util.Logger;

public class LaboratorioDAOPsql implements dao.dao.LaboratorioDAO {

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
                    l.setSoftwares(new SoftwareDAOPsql().selectSoftwareAux(l));
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
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM tb_laboratorio WHERE id = ?");

            pstmt.setInt(1, l.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                l.setNumero(rs.getString("numero"));
                l.setCapacidade(rs.getInt("qtd_alunos"));
                l.setComputadores(rs.getInt("qtd_comps"));
                l.setSoftwares(new SoftwareDAOPsql().selectSoftwareAux(l));
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
                l.setSoftwares(new SoftwareDAOPsql().selectSoftwareAux(l));
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
    public ArrayList<Laboratorio> selectSoftwareLabs(ArrayList<Software> softwares) throws SQLException, ClassNotFoundException {
        ArrayList<Laboratorio> arrayLab = new ArrayList<Laboratorio>();

        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_laboratorio WHERE id NOT IN (SELECT s.id from tb_laboratorio s JOIN aux_sw_lab ss ON s.id = ss.lab WHERE ss.sw = ?)");

            for (Software i : softwares) {
                pstmt.setInt(1, i.getId());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Laboratorio lab = new Laboratorio();
                    lab.setId(rs.getInt("id"));
                    lab.setNumero(rs.getString("numero"));
                    lab.setComputadores(rs.getInt("qtd_comps"));
                    lab.setCapacidade(rs.getInt("qtd_alunos"));
                    arrayLab.add(lab);
                }
            }
        } catch (Exception e) {
            Logger.logSevere(e, LaboratorioDAOPsql.class);
        }

        return arrayLab;
    }

    @Override
    public ArrayList<Laboratorio> selectAvailableLabs(Reserva reserva) throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Laboratorio> arrayLab = selectSoftwareLabs(reserva.getSoftwares());
        ArrayList<Laboratorio> labsDisponiveis = selectLaboratorios();
        ArrayList<Laboratorio> labsReservados = selectReservedLabs(reserva);
        labsDisponiveis.removeAll(labsReservados);
        labsDisponiveis.removeAll(arrayLab);
        return labsDisponiveis;
    }

    @Override
    public Laboratorio selectLaboratorioNumero(Laboratorio lab) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM tb_laboratorio WHERE numero = ?");

            pstmt.setString(1, lab.getNumero());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lab.setId(rs.getInt("id"));
                lab.setComputadores(rs.getInt("qtd_comps"));
                lab.setCapacidade(rs.getInt("qtd_alunos"));
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return lab;
    }

    @Override
    public Integer delete(Laboratorio lab) throws SQLException, ClassNotFoundException {
        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from tb_reserva WHERE laboratorio = ?");
            pstmt.setInt(1, lab.getId());
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                pstmt = conn.prepareStatement("DELETE FROM tb_equipamento WHERE laboratorio = ?");
                pstmt.setInt(1, lab.getId());
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement("DELETE FROM tb_laboratorio WHERE id = ?");
                pstmt.setInt(1, lab.getId());
                pstmt.executeUpdate();
                conn.close();
                return 1;
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LaboratorioDAOPsql.class);
        }
        return 0;
    }

    @Override
    public void atualizar(Laboratorio l) throws SQLException, ClassNotFoundException {
        try (Connection connString = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("DELETE FROM aux_sw_lab WHERE lab = ?");
            pstmt.setInt(1, l.getId());
            pstmt.executeUpdate();

            pstmt = connString.prepareStatement("UPDATE tb_laboratorio SET qtd_comps = ?, qtd_alunos = ? WHERE id = ?");
            pstmt.setInt(1, l.getComputadores());
            pstmt.setInt(2, l.getCapacidade());
            pstmt.setInt(3, l.getId());
            pstmt.executeUpdate();

            pstmt = connString.prepareStatement("INSERT INTO aux_sw_lab VALUES(DEFAULT, ?, ?)");
            for (int i = 0; i < l.getSoftwares().size(); i++) {
                pstmt.setInt(1, l.getSoftwares().get(i).getId());
                pstmt.setInt(2, l.getId());
                pstmt.executeUpdate();
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }
}
