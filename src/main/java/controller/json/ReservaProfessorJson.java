/*
 * Copyright (C) 2017 thaal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller.json;

import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import model.Reserva;
import util.ActiveDirectory;
import util.Logger;

public class ReservaProfessorJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        Reserva r = new Reserva();
        DAOFactory fac = DAOFactory.getFactory();

        HttpSession session = request.getSession();

        r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
        ArrayList<Reserva> reserva = new ArrayList<Reserva>();
        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        ad.login(r.getPessoa());
        reserva = fac.getReservaDAO().selectReserva(r);

        for (Reserva res : reserva) {
            res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
            res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
            res.getPessoa().setShownName(res.getPessoa().getNome() + " " + res.getPessoa().getNomeCompleto().substring(res.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
        }
         
        Logger.logOutput(r.getPessoa().getNomeCompleto() + "(" + r.getPessoa().getUsername() + ") listou as próprias reseravs");
        return util.Json.toJson(reserva);
    }

}
