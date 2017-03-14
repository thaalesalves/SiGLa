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

import util.ActiveDirectory;
import dao.ReservaDAO;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import model.Reserva;

public class ListarReservaDiaAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            HttpSession session = request.getSession();
            
            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();
            r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
            ArrayList<Reserva> reserva = new ArrayList<Reserva>();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
            ad.login(r.getPessoa());

            if (r.getPessoa().getCargo().equals("Professor")) {
                reserva = dao.selectReservaDia(r); // chama reserva de professor
            } else {
                reserva = dao.selectReservaDia(); // chama reservas gerais
            }

            for (Reserva res : reserva) {
                res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
                res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
            }

            request.getSession().setAttribute("reserva", reserva);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }

        return "lista-dia";
    }
}
