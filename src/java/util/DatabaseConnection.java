package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connString = null;
        try {
            Class.forName("org.postgresql.Driver");
            connString = DriverManager.getConnection("jdbc:postgresql://admlab001:5432/sigladb", "sigla", "sigladb");
        } catch (ClassNotFoundException e) {
            Logger.logSevere(e, e.getClass());
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());
        }

        return connString;
    }

}
