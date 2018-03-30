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
import mailsender.Mail;
import mailsender.ReservaRemocaoEquipeMail;
import mailsender.ReservaRemocaoMail;
import model.Pessoa;
import model.Reserva;
import util.Logger;

public class ReservaDiaRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Reserva r = new Reserva();
        try {
            Mail mailProf = new ReservaRemocaoMail();
            Mail mailFunc = new ReservaRemocaoEquipeMail();
            DAOFactory fac = DAOFactory.getFactory();

            r.setId(Integer.parseInt(request.getParameter("reserva_id")));

            fac.getReservaDAO().delete(r);
            
            mailFunc.setReserva(r);
            mailFunc.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mailFunc.sendMail(mailFunc);
            
            mailProf.setReserva(r);
            mailProf.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mailProf.sendMail(mailProf);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") removeu a reserva #" + r.getId() + ".");
        return request.getContextPath() + "/reserva/hoje";
    }
}
