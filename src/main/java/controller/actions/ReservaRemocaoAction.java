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
import util.ActiveDirectory;
import util.Logger;

/**
 *
 * @author Administrator
 */
public class ReservaRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Reserva r = new Reserva();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            Mail mailProf = new ReservaRemocaoMail();
            Mail mailFunc = new ReservaRemocaoEquipeMail();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
            DAOFactory fac = DAOFactory.getFactory();

            r.setId(Integer.parseInt(request.getParameter("reserva_id")));
            r = fac.getReservaDAO().selectReservaId(r);
            r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));
            r.getPessoa().setEmail(ad.getMail(r.getPessoa()));
            r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
            r.setMotivoRemocao(request.getParameter("motivo"));

            fac.getReservaDAO().delete(r);

            mailFunc.setReserva(r);
            mailFunc.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mailFunc.sendMail(mailFunc);

            mailProf.setReserva(r);
            mailProf.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mailProf.sendMail(mailProf);
            ad.closeLdapConnection();
        } catch (Exception e) {
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + " (" + u.getUsername() + ") tentou "
                    + "remover  reserva #" + r.getId());
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao remover reserva.");
            session.setAttribute("status", "error");
            return request.getContextPath() + "/reserva/lista";
        }

        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") removeu a reserva #" + r.getId() + "do banco de dados.");
        session.setAttribute("msg", "Reserva removida.");
        session.setAttribute("status", "success");
        return request.getContextPath() + "/reserva/lista";
    }
}
