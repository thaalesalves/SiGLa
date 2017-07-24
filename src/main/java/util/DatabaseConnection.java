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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            Integer att = 1;

            Properties local = new Properties();
            local.setProperty("host", "127.0.0.1");
            local.setProperty("port", "5432");
            local.setProperty("db", "sigladb");
            local.setProperty("user", "sigla");
            local.setProperty("password", "sigladb");
            local.setProperty("ssl", "false");

            Properties remote = new Properties();
            remote.setProperty("host", "ec2-50-17-217-166.compute-1.amazonaws.com");
            remote.setProperty("port", "5432");
            remote.setProperty("db", "dot13qm593ct7");
            remote.setProperty("user", "dajlivufxcxlms");
            remote.setProperty("password", "35ea0b265a9ad6de0dac4d2c725b7cd02d1ac690a52f73d06bfb82fb94bb2ded");
            remote.setProperty("ssl", "false");

            int i = 0;
            while (i++ < 2) {
                if (i == 1) {
                    try {
                        conn = DriverManager.getConnection("jdbc:postgresql://" + remote.getProperty("host") + ":" + remote.getProperty("port") + "/" + remote.getProperty("db"), remote);
                    } catch (Exception e) {
                        i = i++;
                        Logger.logSevere(e, e.getClass());
                        continue;
                    }
                } else {
                    conn = DriverManager.getConnection("jdbc:postgresql://" + local.getProperty("host") + ":" + local.getProperty("port") + "/" + local.getProperty("db"), local);
                }
            }
        } catch (ClassNotFoundException e) {
            Logger.logSevere(e, e.getClass());
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());
        }

        return conn;
    }
}
