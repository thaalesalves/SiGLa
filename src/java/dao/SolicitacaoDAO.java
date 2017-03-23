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
import model.Reserva;
import model.Solicitacao;
import util.DatabaseConnection;

public class SolicitacaoDAO {

    public int countSolicitacoes() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM solicitacao_semestral");
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
            PreparedStatement pstmt = connString.prepareStatement("SELECT s.id AS solicitacao, s.turma AS turma, s.professor AS professor, s.modulo AS modulo, s.dia_semana AS dia_semana, s.obs AS obs, software.fabricante AS fabricante, software.nome AS software, curso.modalidade AS modalidade, curso.nome AS curso FROM software, curso, solicitacao_semestral AS s WHERE s.softwares = software.id AND s.curso = curso.id");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Solicitacao r = new Solicitacao();

                r.setId(rs.getInt("solicitacao"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
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
