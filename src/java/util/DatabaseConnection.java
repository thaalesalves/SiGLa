package util;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connString = null;
        try {
            Class.forName("org.postgresql.Driver");
            connString = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/usuario", "postgres", "admin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connString;
    }

}
