/**
* Copyright (C) 2016 Thales Alves Pereira
* 
*  This file is part of SiGla.

*   SiGla is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.

*   SiGla is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.

*   You should have received a copy of the GNU General Public License
*   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
**/

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connString = null;
        try {
            Class.forName("org.postgresql.Driver");
            connString = DriverManager.getConnection("jdbc:postgresql://ec2-50-17-217-166.compute-1.amazonaws.com:5432/dot13qm593ct7", "dajlivufxcxlms", "35ea0b265a9ad6de0dac4d2c725b7cd02d1ac690a52f73d06bfb82fb94bb2ded");
        } catch (ClassNotFoundException e) {
            Logger.logSevere(e, e.getClass());
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());
        }

        return connString;
    }

}
