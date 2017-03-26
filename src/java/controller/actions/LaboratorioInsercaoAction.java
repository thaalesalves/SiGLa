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

import dao.LaboratorioDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Laboratorio;

public class LaboratorioInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Laboratorio l = new Laboratorio();
            LaboratorioDAO dao = new LaboratorioDAO();

            l.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
            l.setComputadores(Integer.parseInt(request.getParameter("computadores")));
            l.setNumero(request.getParameter("numero"));

            dao.insertLaboratorio(l);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao efetivar a solicita&ccedil;&atilde;o.");
            return request.getContextPath() + "/reserva/novo";
        }
        session.setAttribute("msg", "Solicita&ccedil;&atilde;o efetuada com sucesso.");
        return request.getContextPath() + "/laboratorio/novo";
    }

}
