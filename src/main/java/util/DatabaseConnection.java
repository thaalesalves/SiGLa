/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public final class DatabaseConnection {

    private static Connection getPsqlConnection() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://"
                    + SiGLa.getDbAddr() + "/"
                    + SiGLa.getDbName(),
                    SiGLa.getDbUser(),
                    SiGLa.getDbPasswd()
            );
        } catch (ClassNotFoundException e) {
            Logger.logSevere(e, DatabaseConnection.class);
        } catch (Exception e) {
            Logger.logSevere(e, DatabaseConnection.class);
        }

        return conn;
    }

    private static Connection getMysqlConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://"
                    + SiGLa.getDbAddr() + "/"
                    + SiGLa.getDbName() + "?serverTimezone=America/Sao_Paulo&useSSL=false",
                    SiGLa.getDbUser(),
                    SiGLa.getDbPasswd()
            );
        } catch (ClassNotFoundException e) {
            Logger.logSevere(e, DatabaseConnection.class);
        } catch (Exception e) {
            Logger.logSevere(e, DatabaseConnection.class);
        }

        return conn;
    }

    public static boolean checkDatabase() throws SQLException {
        Connection conn = null;
        String sql = "";

        try {
            if (SiGLa.getDbms().equals("psql")) {
                conn = getPsqlConnection();
                sql = SiGLa.HOME + "/resources/db/psql.sql";
            } else if (SiGLa.getDbms().equals("mysql")) {
                conn = getMysqlConnection();
                sql = SiGLa.HOME + "/resources/db/mysql.sql";
            }

            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "tb_grupo", new String[]{"TABLE"});

            if (!rs.next()) {
                ScriptRunner sr = new ScriptRunner(conn);
                InputStream is = new FileInputStream(sql);
                Reader reader = new InputStreamReader(is);

                sr.runScript(reader);

                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Logger.logSevere(e, DatabaseConnection.class);
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (SiGLa.getDbms().equals("psql")) {
            return getPsqlConnection();
        } else if (SiGLa.getDbms().equals("mysql")) {
            return getMysqlConnection();
        }

        return null;
    }
}
