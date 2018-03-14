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
import mailsender.ModalMailMessage;
import model.Pessoa;
import util.Logger;

public class EnviarEmailModalAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        String url = request.getParameter("modal-email-pag");
        
        try {
            Mail mail = new ModalMailMessage();
            
            mail.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mail.setRecipient(request.getParameter("modal-email-dest"));
            mail.setSubject(request.getParameter("modal-email-ass"));
            mail.setMessage(request.getParameter("modal-email-msg"));

            mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao enviar o email");
            
            return url;
        }
        
        session.setAttribute("status", "success");
        session.setAttribute("msg", "Email enviado com sucesso");
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");
                Logger.logOutput(u.getNome() + " (" + u.getUsername() + ") acaba de enviar um email para " + request.getParameter("modal-email-msg")  +  ".");
        return url;
    }
}
