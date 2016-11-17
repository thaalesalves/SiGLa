package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DatabaseConnection;
import activedirectory.ActiveDirectory;
import model.Reserva;

public class ReservaDAO {

    private final String DELETE = "DELETE FROM reserva WHERE id = ?";
    private final String SELECT_ALL = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, reserva.tipo AS tipo, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva, laboratorio, software, curso WHERE laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso";
    private final String SELECT_PROF = "SELECT reserva.dia_semana AS dia_semana, reserva.obs AS observacao, reserva.modulo AS modulo, reserva.turma AS turma, curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, reserva.tipo AS tipo, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor FROM reserva, laboratorio, software, curso WHERE laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso AND reserva.professor = ?";
    private final String INSERT_SEMESTRAL = "INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-14'), ?, ?, ?, ?, 1, ?, ?, ?)";
    private final String INSERT_PONTUAL = "";

    public ArrayList<Reserva> selectReserva(Reserva res) throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_PROF);
            pstmt.setString(1, res.getPessoa().getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));
                r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                if (rs.getInt("tipo") == 1) {
                    r.setTipo("Semestral");
                } else {
                    r.setTipo("Pontual");
                }

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return arrayRes;
    }

    public ArrayList<Reserva> selectReserva() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();

                r.getPessoa().setUsername(rs.getString("professor"));
                r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));
                r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
                r.getLab().setNumero(rs.getString("laboratorio"));
                r.getSoftware().setFabricante(rs.getString("fabricante"));
                r.getSoftware().setNome(rs.getString("software"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));
                r.setTurma(rs.getString("turma"));
                r.setModulo(rs.getString("modulo"));
                r.setDiaDaSemana(rs.getString("dia_semana"));

                if (rs.getInt("tipo") == 1) {
                    r.setTipo("Semestral");
                } else {
                    r.setTipo("Pontual");
                }

                arrayRes.add(r);
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return arrayRes;
    }

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
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return qtd;
    }

    public void insertPontual() throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {

        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void insertSemestral(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT_SEMESTRAL);
            
            pstmt.setInt(1, r.getSoftware().getId());
            pstmt.setInt(2, r.getCurso().getId());
            pstmt.setString(3, r.getTurma());
            pstmt.setString(4, r.getPessoa().getUsername());
            pstmt.setString(5, r.getModulo());
            pstmt.setString(6, r.getDiaDaSemana());
            pstmt.setString(7, r.getObservacao());
            
            pstmt.executeUpdate();
            
            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void delete(Reserva r) throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(DELETE);
            
            pstmt.setInt(1, r.getId());
            
            pstmt.executeUpdate();
            
            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }

    private ActiveDirectory ad;

    public void setAd(ActiveDirectory ad) {
        this.ad = ad;
    }
}
