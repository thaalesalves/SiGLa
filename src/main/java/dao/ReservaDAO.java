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
import model.Software;

public class ReservaDAO {

    private final Reserva reserva = new Reserva();
    private final String DELETE = "DELETE FROM reserva WHERE id = ?";
    private final String SELECT_PROF_DIA = "SELECT * FROM reserva WHERE professor = ? AND dia_semana = ?";
    private final String SELECT_ALL_DIA = "SELECT * FROM reserva WHERE dia_semana = ?";
    private final String INSERT = "INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    
    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReservaDia()">
    public ArrayList<Reserva> selectReservaDia() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> ares = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL_DIA);
            pstmt.setString(1, reserva.getDiaSemana());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setId(rs.getInt("id"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setTurma(rs.getString("turma"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaDaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));                
                r.getCurso().setId(rs.getInt("curso"));
                r.getLab().setId(rs.getInt("laboratorio"));
                
                r.setCurso(new CursoDAO().selectId(r.getCurso()));
                r.setSoftwares(new SoftwareDAO().selectSoftwareAux(r));
                r.setLab(new LaboratorioDAO().selectLaboratorio(r.getLab()));
                r.setModulos(new ModuloDAO().selectAux(r));

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

                r.setId(rs.getInt("id"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setTurma(rs.getString("turma"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaDaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));                
                r.getCurso().setId(rs.getInt("curso"));
                r.getLab().setId(rs.getInt("laboratorio"));
                
                r.setCurso(new CursoDAO().selectId(r.getCurso()));
                r.setSoftwares(new SoftwareDAO().selectSoftwareAux(r));
                r.setLab(new LaboratorioDAO().selectLaboratorio(r.getLab()));
                r.setModulos(new ModuloDAO().selectAux(r));

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
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM reserva");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setId(rs.getInt("id"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setTurma(rs.getString("turma"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaDaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));                
                r.getCurso().setId(rs.getInt("curso"));
                r.getLab().setId(rs.getInt("laboratorio"));
                
                r.setCurso(new CursoDAO().selectId(r.getCurso()));
                r.setSoftwares(new SoftwareDAO().selectSoftwareAux(r));
                r.setLab(new LaboratorioDAO().selectLaboratorio(r.getLab()));
                r.setModulos(new ModuloDAO().selectAux(r));

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
            PreparedStatement pstmt = connString.prepareStatement("SELECT * FROM reserva WHERE professor = ?");
            pstmt.setString(1, res.getPessoa().getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.setId(rs.getInt("id"));
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setTurma(rs.getString("turma"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaDaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));                
                r.getCurso().setId(rs.getInt("curso"));
                r.getLab().setId(rs.getInt("laboratorio"));
                
                r.setCurso(new CursoDAO().selectId(r.getCurso()));
                r.setSoftwares(new SoftwareDAO().selectSoftwareAux(r));
                r.setLab(new LaboratorioDAO().selectLaboratorio(r.getLab()));
                r.setModulos(new ModuloDAO().selectAux(r));

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return arrayRes;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectReservaId(Reserva)">
    public Reserva selectReservaId(Reserva r) throws SQLException, ClassNotFoundException, NullPointerException {
        try (Connection conn = util.DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reserva WHERE id = ?");            
            pstmt.setInt(1, r.getId());            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                r.setQtdAlunos(rs.getInt("qtd_alunos"));
                r.setTurma(rs.getString("turma"));
                r.getPessoa().setUsername(rs.getString("professor"));
                r.setDiaDaSemana(rs.getString("dia_semana"));
                r.setObservacao(rs.getString("obs"));                
                r.getCurso().setId(rs.getInt("curso"));
                r.getLab().setId(rs.getInt("laboratorio"));
                
                r.setCurso(new CursoDAO().selectId(r.getCurso()));
                r.setSoftwares(new SoftwareDAO().selectSoftwareAux(r));
                r.setLab(new LaboratorioDAO().selectLaboratorio(r.getLab()));
                r.setModulos(new ModuloDAO().selectAux(r));
            }
            
            conn.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }
        
        return r;
    }// </editor-fold>
    
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

    // <editor-fold defaultstate="collapsed" desc="Método próprio: insert(Reserva)">
    public Reserva insert(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT);

            pstmt.setInt(1, r.getLab().getId());
            pstmt.setInt(2, r.getCurso().getId());
            pstmt.setInt(3, r.getQtdAlunos());
            pstmt.setString(4, r.getTurma());
            pstmt.setString(5, r.getPessoa().getUsername());
            pstmt.setString(6, r.getDiaDaSemana());
            pstmt.setString(7, r.getObservacao());

            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                r.setId(rs.getInt(1));
            }

            LaboratorioDAO ldao = new LaboratorioDAO();            
            r.setLab(ldao.selectLaboratorio(r.getLab()));
            
            pstmt = connString.prepareStatement("INSERT INTO sw_res VALUES(NEXTVAL('seq_sw_res'), ?, ?)                              ");
            
            for (Software s : r.getSoftwares()) {
                pstmt.setInt(1, s.getId());
                pstmt.setInt(2, r.getId());
                
                pstmt.executeUpdate();
            }
            
            pstmt = connString.prepareStatement("INSERT INTO modulo_res VALUES(NEXTVAL('seq_modulo_res'), ?, ?)");
            
            for (int i = 0; i < r.getModulos().size(); i++) {
                pstmt.setInt(1, r.getId());
                pstmt.setInt(2, r.getModulos().get(i).getId());
                pstmt.executeUpdate();
            }
            
            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
        
        return r;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: delete(Reserva)">
    public void delete(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("DELETE FROM sw_res WHERE res = ?");
            pstmt.setInt(1, r.getId());
            pstmt.executeUpdate();
            
            pstmt = connString.prepareStatement("DELETE FROM modulo_res WHERE res = ?");
            pstmt.setInt(1, r.getId());
            pstmt.executeUpdate();
            
            pstmt = connString.prepareStatement(DELETE);
            pstmt.setInt(1, r.getId());
            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }//</editor-fold>
}
