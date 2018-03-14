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
import model.Modulo;
import model.Pessoa;
import model.Reserva;
import util.Logger;

public class ReservaAtualizacaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Reserva r = new Reserva();
        try {
            DAOFactory fac = DAOFactory.getFactory();
            String[] modulos = request.getParameterValues("modalModuloCombo");

            r.setId(Integer.parseInt(request.getParameter("reserva-id")));
            r.getPessoa().setUsername(request.getParameter("modalProfessoresCombo"));
            r.setDiaDaSemana(request.getParameter("modalDiaCombo"));
            r.getLab().setId(Integer.parseInt(request.getParameter("modalLabCombo")));

            for (String i : modulos) {
                Modulo m = new Modulo();
                m.setId(Integer.parseInt(i.trim()));
                r.getModulos().add(m);
            }

            fac.getReservaDAO().update(r);
        } catch (Exception e) {
            session.setAttribute("msg", "Erro ao atualizar dados de reserva.");
            session.setAttribute("status", "error");
            Logger.logSevere(e, ReservaAtualizacaoAction.class);
            return request.getContextPath() + "/reserva/lista";
        }

        session.setAttribute("msg", "Dados de reserva atualizados.");
        session.setAttribute("status", "success");
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNome() + " (" + u.getUsername() + ") alterou a reserva #" + r.getId() + ".");
        return request.getContextPath() + "/reserva/lista";
    }
}
