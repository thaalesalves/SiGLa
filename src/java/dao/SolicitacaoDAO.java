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
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Software;
import model.Solicitacao;
import util.DatabaseConnection;

public class SolicitacaoDAO {

    private final String INSERT_AUX = "INSERT INTO sw_soli VALUES(NEXTVAL('seq_sw_soli'), ?, ?)";
    private final String INSERT_RETURN = "INSERT INTO solicitacao VALUES(NEXTVAL('seq_soli'), ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    private final String SELECT_ID = "SELECT s.id AS soli, s.qtd_alunos, s.turma, s.professor, s.modulo, s.dia_semana, s.obs, curso.id AS c_id, curso.modalidade AS modalidade, curso.nome AS curso, software.id AS sw_id, software.fabricante AS fabricante, software.nome AS software FROM solicitacao AS s INNER JOIN curso ON (curso.id = s.curso) INNER JOIN sw_soli ON (sw_soli.res = s.id) INNER JOIN software ON (software.id = sw_soli.sw) WHERE s.id = ?";
    private final String SELECT_ALL = "SELECT s.id AS soli, s.qtd_alunos, s.turma, s.professor, s.modulo, s.dia_semana, s.obs, curso.id AS c_id, curso.modalidade AS modalidade, curso.nome AS curso, software.id AS sw_id, software.fabricante AS fabricante, software.nome AS software FROM solicitacao AS s INNER JOIN curso ON (curso.id = s.curso) INNER JOIN sw_soli ON (sw_soli.res = s.id) INNER JOIN software ON (software.id = sw_soli.sw)";
    private final String DELETE = "DELETE FROM solicitacao WHERE id = ?";
    private final String DELETE_AUX = "DELETE FROM sw_soli WHERE res = ?";
    private final String COUNT = "SELECT COUNT(id) FROM solicitacao";

    public Solicitacao selectSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT curso.id AS curso_id, software.id AS sw_id, s.qtd_alunos AS qtd_alunos, s.turma AS turma, s.professor AS professor, s.modulo AS modulo, s.dia_semana AS dia_semana, s.obs AS obs, software.fabricante AS fabricante, software.nome AS software, curso.modalidade AS modalidade, curso.nome AS curso FROM software, curso, solicitacao AS s WHERE s.softwares = software.id AND s.curso = curso.id AND s.id = ?");

//            Select * from solicitacao 
//            
//            while rs.next
//                     r = new reserva
//                     r.setQtdeAlunos
//                    r.setModulo
//                            Usuario u = new Usuario
//                                    u.setLogin(rs.getProfessor)
//                     r.setProfessor(u)
//                             r.setCurso (new CursoDAO().consultarPorID(Rs.getIdCurso)())
//                             r.setSoftware(new SoftwareDAO().consultarPorSolicitacao(r)))
            pstmt.setInt(1, s.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setQtdAlunos(rs.getInt("qtd_alunos"));
                s.setTurma(rs.getString("turma"));
                s.getPessoa().setUsername(rs.getString("professor"));
                s.setModulo(rs.getString("modulo"));
                s.setDiaSemana(rs.getString("dia_semana"));
                s.setObservacao(rs.getString("obs"));                
                s.getCurso().setId(rs.getInt("curso_id"));
                //s.getSoftware().setId(rs.getInt("sw_id"));
                
                s.setCurso(new CursoDAO().selectId(s.getCurso()));
//                s.setSoftware(new SoftwareDAO().selectId(s.getSoftware()));
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return s;
    }

    public Solicitacao insertSolicitacoes(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT_RETURN);

            pstmt.setInt(1, s.getCurso().getId()); // curso
            pstmt.setInt(2, s.getQtdAlunos()); // qtd de alunos
            pstmt.setString(3, s.getTurma()); // turma
            pstmt.setString(4, s.getPessoa().getUsername()); // professor
            pstmt.setString(5, s.getModulo()); // modulo
            pstmt.setString(6, s.getDiaSemana()); // dia da semana
            pstmt.setString(7, s.getObservacao()); // observacao

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setId(rs.getInt(1));
            }

            pstmt = connString.prepareStatement(INSERT_AUX);

            for (int i = 0; i < s.getSoftwares().size(); i++) {
                pstmt.setInt(1, s.getSoftwares().get(i).getId());
                pstmt.setInt(2, s.getId());
                pstmt.executeUpdate();
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return s;
    }

    public int countSolicitacoes() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(COUNT);
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

    public ArrayList<Solicitacao> selectSolicitacao() throws SQLException, ClassNotFoundException {
        ArrayList<Solicitacao> arrayRes = new ArrayList<Solicitacao>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM solicitacao");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Solicitacao s = new Solicitacao();
                
                s.setId(rs.getInt("id"));
                s.setQtdAlunos(rs.getInt("qtd_alunos"));
                s.setTurma(rs.getString("turma"));
                s.getPessoa().setUsername(rs.getString("professor"));
                s.setModulo(rs.getString("modulo"));
                s.setDiaSemana(rs.getString("dia_semana"));
                s.setObservacao(rs.getString("obs"));
                s.getCurso().setId(rs.getInt("curso"));
               // s.getSoftware().setId(rs.getInt("sw"));
                
                s.setCurso(new CursoDAO().selectId(s.getCurso()));
                s.setSoftwares(new SoftwareDAO().selectSoftwareAux(s));
                
                arrayRes.add(s);
            }
            /*while (rs.next()) {
                Solicitacao s = new Solicitacao();

                s.setId(rs.getInt("soli"));
                s.setQtdAlunos(rs.getInt("qtd_alunos"));
                s.setTurma(rs.getString("turma"));
                s.getPessoa().setUsername(rs.getString("professor"));
                s.setModulo(rs.getString("modulo"));
                s.setDiaSemana(rs.getString("dia_semana"));
                s.setObservacao(rs.getString("obs"));
                
                
                s.getCurso().setModalidade(rs.getString("modalidade"));
                s.getCurso().setNome(rs.getString("curso"));
                s.getSoftware().setFabricante(rs.getString("fabricante"));
                s.getSoftware().setNome(rs.getString("software"));

                arrayRes.add(s);
            }*/

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    }

    public void deleteSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(DELETE_AUX);

            pstmt.setInt(1, s.getId());

            pstmt.executeUpdate();

            pstmt = connString.prepareStatement(DELETE);

            pstmt.setInt(1, s.getId());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }
}
