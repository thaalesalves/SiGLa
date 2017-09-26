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
package dao.dao.mysql;

import dao.dao.psql.CursoDAOPsql;
import dao.dao.psql.SoftwareDAOPsql;
import dao.dao.psql.ModuloDAOPsql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Solicitacao;
import util.DatabaseConnection;

public class SolicitacaoDAOMysql implements dao.dao.SolicitacaoDAO {
@Override
    public Solicitacao selectSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_solicitacao WHERE id = ?");
            pstmt.setInt(1, s.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setQtdAlunos(rs.getInt("qtd_alunos"));
                s.setTurma(rs.getString("turma"));
                s.getPessoa().setUsername(rs.getString("professor"));
                s.setDiaSemana(rs.getString("dia_semana"));
                s.setObservacao(rs.getString("obs"));
                s.getCurso().setId(rs.getInt("curso"));

                s.setCurso(new CursoDAOPsql().selectId(s.getCurso()));
                s.setSoftwares(new SoftwareDAOPsql().selectSoftwareAux(s));
                s.setModulos(new ModuloDAOPsql().selectAux(s));
            }

            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return s;
    }
@Override
    public Solicitacao insertSolicitacoes(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("INSERT INTO tb_solicitacao VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, s.getCurso().getId()); // curso
            pstmt.setInt(2, s.getQtdAlunos()); // qtd de alunos
            pstmt.setString(3, s.getTurma()); // turma
            pstmt.setString(4, s.getPessoa().getUsername()); // professor
            pstmt.setString(5, s.getDiaSemana()); // dia da semana
            pstmt.setString(6, s.getObservacao()); // observacao

            pstmt.executeUpdate();

            pstmt = connString.prepareStatement("SELECT id FROM tb_solicitacao");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setId(rs.getInt(1));
            }

            pstmt = connString.prepareStatement("INSERT INTO aux_sw_soli VALUES(DEFAULT, ?, ?)");

            for (int i = 0; i < s.getSoftwares().size(); i++) {
                pstmt.setInt(1, s.getSoftwares().get(i).getId());
                pstmt.setInt(2, s.getId());
                pstmt.executeUpdate();
            }

            pstmt = connString.prepareStatement("INSERT INTO aux_modulo_soli VALUES(DEFAULT, ?, ?)");

            for (int i = 0; i < s.getModulos().size(); i++) {
                pstmt.setInt(1, s.getId());
                pstmt.setInt(2, s.getModulos().get(i).getId());
                pstmt.executeUpdate();
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return s;
    }
@Override
    public int countSolicitacoes() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(id) AS count FROM tb_solicitacao");
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
    public ArrayList<Solicitacao> selectSolicitacao() throws SQLException, ClassNotFoundException {
        ArrayList<Solicitacao> arrayRes = new ArrayList<Solicitacao>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM tb_solicitacao");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Solicitacao s = new Solicitacao();

                s.setId(rs.getInt("id"));
                s.setQtdAlunos(rs.getInt("qtd_alunos"));
                s.setTurma(rs.getString("turma"));
                s.getPessoa().setUsername(rs.getString("professor"));
                s.setDiaSemana(rs.getString("dia_semana"));
                s.setObservacao(rs.getString("obs"));
                s.getCurso().setId(rs.getInt("curso"));

                s.setCurso(new CursoDAOPsql().selectId(s.getCurso()));
                s.setSoftwares(new SoftwareDAOPsql().selectSoftwareAux(s));
                s.setModulos(new ModuloDAOPsql().selectAux(s));

                arrayRes.add(s);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    }

    @Override
    public void deleteSolicitacao(Solicitacao s) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("DELETE FROM aux_sw_soli WHERE res = ?");
            pstmt.setInt(1, s.getId());
            pstmt.executeUpdate();

            pstmt = connString.prepareStatement("DELETE FROM aux_modulo_soli WHERE res = ?");
            pstmt.setInt(1, s.getId());
            pstmt.executeUpdate();

            pstmt = connString.prepareStatement("DELETE FROM tb_solicitacao WHERE id = ?");
            pstmt.setInt(1, s.getId());
            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }
}
