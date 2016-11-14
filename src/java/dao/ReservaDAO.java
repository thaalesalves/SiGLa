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

    private final String SELECT_ALL = "SELECT curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, reserva.tipo AS tipo, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor, turma.semestre AS semestre, turma.turma AS turma FROM reserva, laboratorio, software, turma, curso WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso";
    private final String SELECT_PROF = "SELECT curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, reserva.tipo AS tipo, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor, turma.semestre AS semestre, turma.turma AS turma FROM reserva, laboratorio, software, turma, curso WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso AND reserva.professor = ?";
    private final String INSERT_PONTUAL = "";
    private final String INSERT_SEMESTRAL = "";

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
                r.getTurma().setSemestre(rs.getInt("semestre"));
                r.getTurma().setTurma(rs.getString("turma"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));

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
                r.getTurma().setSemestre(rs.getInt("semestre"));
                r.getTurma().setTurma(rs.getString("turma"));
                r.getCurso().setModalidade(rs.getString("modalidade"));
                r.getCurso().setNome(rs.getString("curso"));
                r.setId(rs.getInt("reserva"));

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

    public void insertSemestral() throws SQLException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {

        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }

    private ActiveDirectory ad;

    public void setAd(ActiveDirectory ad) {
        this.ad = ad;
    }
}
