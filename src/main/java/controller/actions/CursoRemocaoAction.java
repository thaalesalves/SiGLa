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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Curso;
import model.Pessoa;
import util.Logger;

public class CursoRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        Curso c = new Curso();
        HttpSession session = request.getSession();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            c.setId(Integer.parseInt(request.getParameter("curso_id")));
            c = fac.getCursoDAO().selectId(c);
            fac.getCursoDAO().delete(c);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "remover o curso " + c.getModalidade() + " " + c.getNome() + "(#" + c.getId() + ").");
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao remover curso");
            return request.getContextPath() + "/curso/lista";
        }

        session.setAttribute("status", "success");
        session.setAttribute("msg", "Curso removido");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") acaba de remover o curso " + c.getNome() + "(#" + c.getId() + ".");
        return request.getContextPath() + "/curso/lista";
    }

}
