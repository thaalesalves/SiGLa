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

import dao.CursoDAO;
import dao.ReservaDAO;
import dao.SoftwareDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailsender.Mail;
import mailsender.MailSemestral;
import model.Pessoa;
import model.Reserva;

public class InserirReservaSemestralAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            Mail mail = new MailSemestral();

            CursoDAO cdao = new CursoDAO();
            SoftwareDAO sdao = new SoftwareDAO();
            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();

            r.setPessoa((Pessoa) session.getAttribute("pessoa"));
            r.setModulo(request.getParameter("modulo"));
            r.setTurma(request.getParameter("turma"));
            r.setQtd(Integer.parseInt(request.getParameter("qtd")));
            r.getSoftware().setId(Integer.parseInt(request.getParameter("softwares").trim()));
            r.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));

            if (request.getParameter("obs") == null) {
                r.setObservacao("Nenhuma informada.");
            } else {
                r.setObservacao(request.getParameter("obs"));
            }

            r.setSoftware(sdao.selectId(r.getSoftware()));
            r.setCurso(cdao.selectId(r.getCurso()));

            mail.setReserva(r);

            mail.sendMail(mail);

            dao.insertSemestral(r);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("status", "error");
            return request.getContextPath() + "/reserva/listar-semestral";
        }
        session.setAttribute("status", "success");
        return request.getContextPath() + "/reserva/listar-semestral";
    }
}
