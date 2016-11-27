package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Curso;
import util.DatabaseConnection;

public class CursoDAO {

    private final String SELECT_ALL = "SELECT * FROM curso";
    private final String SELECT_ID = "SELECT nome, modalidade FROM curso WHERE id = ?";
    private final String DELETE = "DELETE FROM curso WHERE id = ?";
    private final String INSERT = "INSERT INTO curso VALUES(NEXTVAL('seq_curso'), ?, ?)";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectAll()">
    public ArrayList<Curso> selectAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Curso> ac = new ArrayList<Curso>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setModalidade(rs.getString("modalidade"));

                ac.add(c);
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
            throw e;
        }

        return ac;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectId(Curso)">
    public Curso selectId(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ID);
            pstmt.setInt(1, c.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                c.setNome(rs.getString("nome"));
                c.setModalidade(rs.getString("modalidade"));
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return c;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: insert(Curso)">
    public void insert(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(INSERT);

            pstmt.setString(1, c.getNome());
            pstmt.setString(2, c.getModalidade());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: delete(Curso)">
    public void delete(Curso c) throws SQLException, NullPointerException, ClassNotFoundException {
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(DELETE);

            pstmt.setInt(1, c.getId());

            pstmt.executeUpdate();

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }
    }//</editor-fold>
}
