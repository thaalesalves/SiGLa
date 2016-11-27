package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Software;
import util.DatabaseConnection;

public class SoftwareDAO {

    private final String SELECT_ALL = "SELECT id, nome, fabricante FROM software;";
    private final String SELECT_ID = "SELECT fabricante, nome FROM software WHERE id = ?";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectAll()">
    public ArrayList<Software> selectAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ArrayList<Software> sws = new ArrayList<Software>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Software sw = new Software();

                sw.setId(rs.getInt("id"));
                sw.setNome(rs.getString("nome"));
                sw.setFabricante(rs.getString("fabricante"));

                sws.add(sw);
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
            throw e;
        }

        return sws;
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Método próprio: selectId(Software)">
    public Software selectId(Software s) throws SQLException, NullPointerException, ClassNotFoundException {

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ID);
            pstmt.setInt(1, s.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                s.setNome(rs.getString("nome"));
                s.setFabricante(rs.getString("fabricante"));
            }

            connString.close();
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return s;
    }//</editor-fold>
}
