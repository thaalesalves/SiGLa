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

package controller.actions;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        try {
            ActiveDirectory ad = (ActiveDirectory) request.getSession().getAttribute("ad");
            Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
            
            ad.closeLdapConnection();
            request.getSession().invalidate();
            
            Logger.logOutput("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()) + "]: "
                        + p.getUsername() + " acaba de fazer logoff no SiGLa.");
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return request.getContextPath();
    }
}
