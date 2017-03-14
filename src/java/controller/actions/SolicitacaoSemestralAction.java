/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
*
 */
package controller.actions;

import dao.ReservaDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reserva;
import util.ActiveDirectory;

public class SolicitacaoSemestralAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            HttpSession session = request.getSession();

            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
            ReservaDAO rdao = new ReservaDAO();
            ArrayList<Reserva> reserva = rdao.selectSolicitacoes();

            for (Reserva r : reserva) {
                r.getPessoa().setNome(ad.getGivenName(r));
                r.getPessoa().setNomeCompleto(ad.getCN(r));
            }
            
            session.setAttribute("dados-solicitacoes", reserva);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
        
        return request.getContextPath() + "/reserva/solicitacoes";
    }

}