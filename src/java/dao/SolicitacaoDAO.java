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
import model.Solicitacao;
import util.DatabaseConnection;

public class SolicitacaoDAO {
    
    public void deleteSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("DELETE FROM solicitacao WHERE id = ?");
            
            pstmt.setInt(1, s.getId());
            
            pstmt.executeUpdate();
            
            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }
    
    public boolean insertSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("INSERT INTO solicitacao VALUES(NEXTVAL('seq_soli'), ?, ?, ?, ?, ?, ?, ?, ?)");
            
            pstmt.setInt(1, s.getSoftware().getId()); // software
            pstmt.setInt(2, s.getCurso().getId()); // curso
            pstmt.setInt(3, s.getQtdAlunos()); // qtd de alunos
            pstmt.setString(4, s.getTurma()); // turma
            pstmt.setString(5, s.getPessoa().getUsername()); // professor
            pstmt.setString(6, s.getModulo()); // modulo
            pstmt.setString(7, s.getDiaSemana()); // dia da semana
            pstmt.setString(8, s.getObservacao()); // observacao
            
            pstmt.executeUpdate();
            
            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            return false;
        }
        
        return true;
    }
    
    public int countSolicitacoes() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM solicitacao");
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
            PreparedStatement pstmt = connString.prepareStatement("SELECT curso.id AS curso_id, software.id AS sw_id, s.qtd_alunos AS qtd_alunos, s.id AS solicitacao, s.turma AS turma, s.professor AS professor, s.modulo AS modulo, s.dia_semana AS dia_semana, s.obs AS obs, software.fabricante AS fabricante, software.nome AS software, curso.modalidade AS modalidade, curso.nome AS curso FROM software, curso, solicitacao AS s WHERE s.softwares = software.id AND s.curso = curso.id ORDER BY s.id DESC");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Solicitacao r = new Solicitacao();

                r.setId(rs.getInt("solicitacao"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.getCurso().setId(rs.getInt("curso_id"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.getSoftware().setId(rs.getInt("sw_id"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    }
}
