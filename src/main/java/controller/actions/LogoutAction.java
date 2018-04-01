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
package controller.actions;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import util.ActiveDirectory;
import util.Logger;

public class LogoutAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
        
        try {
            ActiveDirectory ad = (ActiveDirectory) request.getSession().getAttribute("ad");

            ad.closeLdapConnection();
            request.getSession().invalidate();

        } catch (Exception e) {
            Logger.logOutput("Houve um erro quando " + p.getNomeCompleto() + " (" + p.getUsername() + ") tentou "
                    + "fazer logout do SiGLa.");
            util.Logger.logSevere(e, this.getClass());
        }
        
        Logger.logOutput(p.getNomeCompleto() + " (" + p.getUsername() + ") acaba de fazer logout do SiGLa.");
        return request.getContextPath();
    }
}
