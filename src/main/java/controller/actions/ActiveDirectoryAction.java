/*
 * Copyright (C) 2017 thaal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SiGLa;

public class ActiveDirectoryAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        String op = request.getParameter("op");
        String val = request.getParameter("val");
        String netbios = request.getParameter("netbios");
        String port = "";

        try {
            if (op.equals("dominio")) {
                SiGLa.writeProperty("sigla.auth.domain", val);
                SiGLa.writeProperty("sigla.auth.netbios", netbios);
            } else if (op.equals("auth")) {
                if (val.equals("ldaps")) {
                    port = "636";
                } else if (val.equals("ldap")) {
                    port = "389";
                }

                SiGLa.writeProperty("sigla.auth.method", val);
                SiGLa.writeProperty("sigla.auth.port", port);
            } else if (op.equals("host")) {
                SiGLa.writeProperty("sigla.auth.host", val);
            }
        } catch (Exception e) {
            SiGLa.writeProperty("sigla.auth.domain", "null");
            SiGLa.writeProperty("sigla.auth.netbios", "null");
            SiGLa.writeProperty("sigla.auth.method", "null");
            SiGLa.writeProperty("sigla.auth.port", "null");
            SiGLa.writeProperty("sigla.auth.host", "null");
            util.Logger.logSevere(e, ConfigurationAction.class);
        }

        return "";
    }
}
