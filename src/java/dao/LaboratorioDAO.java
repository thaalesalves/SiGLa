package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseConnection;

public class LaboratorioDAO {

    private final String SELECT_QTD = "SELECT COUNT(*) FROM laboratorio";

    // <editor-fold defaultstate="collapsed" desc="Método próprio: qtdLabs()">
    public int qtdLabs() throws SQLException, ClassNotFoundException {
        int qtd = 0;

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_QTD);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("count");
            }

            connString.close();
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }

        return qtd;
    }//</editor-fold>
}
