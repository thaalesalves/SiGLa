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
package controller;

import controller.actions.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlmightyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            Class action = Class.forName("controller.actions." + request.getParameter("acao") + "Action");
            ICommand cmd = (ICommand) action.newInstance();

            //request.getRequestDispatcher(cmd.execute(request, response)).forward(request, response);
            response.sendRedirect(cmd.execute(request, response));
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: doPost(HttpServletRequest, HttpServletResponse)">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: doGet(HttpServletRequest, HttpServletResponse)">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: getServletInfo()">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
