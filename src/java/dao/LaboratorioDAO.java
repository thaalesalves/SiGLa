package dao;

import java.sql.*;
import java.util.*;
import util.*;

public class LaboratorioDAO {    
    
    public int qtdLabs() throws SQLException, ClassNotFoundException {
        int qtd = 0;
        
        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement("SELECT COUNT(*) FROM laboratorio");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                qtd = rs.getInt("count");
            }
            
            connString.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return qtd;
    }
}
