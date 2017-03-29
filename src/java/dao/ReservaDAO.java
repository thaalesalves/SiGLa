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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DatabaseConnection;
import model.Reserva;

public class ReservaDAO {

    private Reserva reserva = new Reserva();
    private final String DELETE = "DELETE FROM reserva WHERE id = ?";
    private final String SELECT_ALL = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva INNER JOIN laboratorio ON(laboratorio.id = reserva.laboratorio) INNER JOIN software ON (software.id = reserva.softwares) INNER JOIN curso ON (curso.id = reserva.curso) ORDER BY reserva.id DESC";
    private final String SELECT_PROF = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva INNER JOIN laboratorio ON(laboratorio.id = reserva.laboratorio) INNER JOIN software ON (software.id = reserva.softwares) INNER JOIN curso ON (curso.id = reserva.curso) WHERE professor = ?";
    private final String SELECT_PROF_DIA = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva INNER JOIN laboratorio ON(laboratorio.id = reserva.laboratorio) INNER JOIN software ON (software.id = reserva.softwares) INNER JOIN curso ON (curso.id = reserva.curso) WHERE professor = ? AND dia_semana = ?";
    private final String SELECT_ALL_DIA = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva INNER JOIN laboratorio ON(laboratorio.id = reserva.laboratorio) INNER JOIN software ON (software.id = reserva.softwares) INNER JOIN curso ON (curso.id = reserva.curso) WHERE dia_semana = ?";
    private final String INSERT = "INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), ?, ?, ?, ?, ?, ?, ?, ?)";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReservaDia()">
    public ArrayList<Reserva> selectReservaDia() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> ares = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL_DIA);
            pstmt.setString(1, reserva.getDiaSemana());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                ares.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return ares;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReservaDia(Reserva)">
    public ArrayList<Reserva> selectReservaDia(Reserva res) throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_PROF_DIA);
            pstmt.setString(1, res.getPessoa().getUsername());
            pstmt.setString(2, reserva.getDiaSemana());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReserva()">
    public ArrayList<Reserva> selectReserva() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReserva(Reserva)">
    public ArrayList<Reserva> selectReserva(Reserva res) throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_PROF);
            pstmt.setString(1, res.getPessoa().getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: qtdReservasDia()">
    public int qtdReservasDia() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM reserva WHERE dia_semana = ?");

            pstmt.setString(1, reserva.getDiaSemana());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("count");
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return qtd;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: qtdReservas()">
    public int qtdReservas() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM reserva");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("count");
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return qtd;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: insertSemestral(Reserva)">
    public void insertSemestral(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT);

            pstmt.setInt(1, r.getLab().getId());
            pstmt.setInt(2, r.getSoftware().getId());
            pstmt.setInt(3, r.getCurso().getId());
            pstmt.setString(4, r.getTurma());
            pstmt.setString(5, r.getPessoa().getUsername());
            pstmt.setString(6, r.getModulo());
            pstmt.setString(7, r.getDiaDaSemana());
            pstmt.setString(8, r.getObservacao());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: delete(Reserva)">
    public void delete(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(DELETE);

            pstmt.setInt(1, r.getId());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }//</editor-fold>
}
