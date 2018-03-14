/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.actions;

import dao.DAOFactory;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Curso;
import model.Pessoa;
import model.Reserva;
import model.Software;
import util.Logger;

public class ReservaInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            Reserva reserva = new Reserva();
            DAOFactory fac = DAOFactory.getFactory();

            ArrayList<Software> softwares = fac.getSoftwareDAO().selectAll();
            ArrayList<Curso> cursos = fac.getCursoDAO().selectAll();

            reserva.setCursos(cursos);
            reserva.setSoftwares(softwares);

            session.setAttribute("reserva", reserva);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNome() + " (" + u.getUsername() + ") criou uma reserva.");
        return request.getContextPath() + "/reserva/novo";
    }
}
