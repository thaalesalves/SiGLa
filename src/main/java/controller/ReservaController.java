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
package controller;

import dao.ReservaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import model.Reserva;
import util.ActiveDirectory;

/**
 *
 * @author thaal
 */
public class ReservaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Reserva r = new Reserva();
            ReservaDAO dao = new ReservaDAO();

            HttpSession session = request.getSession();
            String acao = request.getParameter("acao");

            if (acao.equals("lista-hoje")) {
                r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
                ArrayList<Reserva> reserva = new ArrayList<Reserva>();
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
                ad.login(r.getPessoa());

                reserva = dao.selectReservaDia();

                for (Reserva res : reserva) {
                    res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
                    res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
                    res.getPessoa().setShownName(res.getPessoa().getNome() + " " + res.getPessoa().getNomeCompleto().substring(res.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                }

                out.println(util.Json.toCuteJson(reserva));
            } else if (acao.equals("lista-tudo")) {
                r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
                ArrayList<Reserva> reserva = new ArrayList<Reserva>();
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
                ad.login(r.getPessoa());
                reserva = dao.selectReserva();

                for (Reserva res : reserva) {
                    res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
                    res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
                    res.getPessoa().setShownName(res.getPessoa().getNome() + " " + res.getPessoa().getNomeCompleto().substring(res.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                }

                out.println(util.Json.toCuteJson(reserva));
            } else if (acao.equals("prof-hoje")) {
                r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
                ArrayList<Reserva> reserva = new ArrayList<Reserva>();
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
                ad.login(r.getPessoa());

                reserva = dao.selectReservaDia(r);

                for (Reserva res : reserva) {
                    res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
                    res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
                    res.getPessoa().setShownName(res.getPessoa().getNome() + " " + res.getPessoa().getNomeCompleto().substring(res.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                }

                out.println(util.Json.toCuteJson(reserva));
            } else if (acao.equals("prof-tudo")) {
                r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
                ArrayList<Reserva> reserva = new ArrayList<Reserva>();
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
                ad.login(r.getPessoa());
                reserva = dao.selectReserva(r);

                for (Reserva res : reserva) {
                    res.getPessoa().setNome(ad.getGivenName(res.getPessoa()));
                    res.getPessoa().setNomeCompleto(ad.getCN(res.getPessoa()));
                    res.getPessoa().setShownName(res.getPessoa().getNome() + " " + res.getPessoa().getNomeCompleto().substring(res.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                }

                out.println(util.Json.toCuteJson(reserva));
            }

        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
