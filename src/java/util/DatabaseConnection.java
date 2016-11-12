package util;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connString = null;
        try {
            Class.forName("org.postgresql.Driver");
            connString = DriverManager.getConnection("jdbc:postgresql://admlab001.umc.br:5432/sigladb", "sigla", "sigladb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connString;
    }

}
