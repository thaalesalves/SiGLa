package dao;

import java.sql.*;
import java.util.*;
import util.*;

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
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return qtd;
    }//</editor-fold>
}
