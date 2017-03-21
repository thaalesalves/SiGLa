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
package controller;

import dao.EquipamentoDAO;
import dao.LaboratorioDAO;
import dao.ReservaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
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
 * @author Administrator
 */
public class CounterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, ClassNotFoundException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        HttpSession session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            if (acao.equals("reserva")) {
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

                ReservaDAO rdao = new ReservaDAO();

                ArrayList<Reserva> r = rdao.selectSolicitacaoProf();
                char quote = '"';

                out.println("{" + quote + "count" + quote + " : " + quote + rdao.countSolicitacoes() + quote + "}");
                for (Reserva i : r) {
                    i.getPessoa().setNomeCompleto(ad.getCN(i));
                    out.println(", {" + quote + "prof" + quote + " : " + quote + i.getPessoa().getNomeCompleto().substring(0, i.getPessoa().getNomeCompleto().indexOf(" ")) + i.getPessoa().getNomeCompleto().substring(i.getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "}");
                }
            } else if (acao.equals("teste")) {
                out.println("<style>"
                        + ".id0 { text-indent: 0px; margin-bottom: -0px; margin-top: -0px; } "
                        + ".id1 { text-indent: 25px; margin-bottom: -0px; margin-top: -0px; } "
                        + ".id2 { text-indent: 50px; margin-bottom: -0px; margin-top: -0px; } "
                        + ".id3 { text-indent: 75px; margin-bottom: -0px; margin-top: -0px; } "
                        + ".id4 { text-indent: 100px margin-bottom: -0px; margin-top: -0px; } "
                        + "</style>");

                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

                ReservaDAO rdao = new ReservaDAO();
                LaboratorioDAO ldao = new LaboratorioDAO();
                EquipamentoDAO edao = new EquipamentoDAO();

                ArrayList<Reserva> r = rdao.selectSolicitacaoProf();
                char quote = '"';

                for (Reserva i : r) {
                    i.getPessoa().setNomeCompleto(ad.getCN(i.getPessoa()));
                }

                out.println(""
                        + "<p class='id0'>{</p>"
                        + "<p class='id1'>" + quote + "counter" + quote + " : [{ </p>"
                        + "<p class='id2'>" + quote + "solicitacoes" + quote + " : " + quote + rdao.countSolicitacoes() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "reservas" + quote + " : " + quote + rdao.qtdReservas() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "reservas_hoje" + quote + " : " + quote + rdao.qtdReservasDia() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "laboratorios" + quote + " : " + quote + ldao.qtdLabs() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "computadores" + quote + " : " + quote + edao.qtdEquip() + quote + ",</p>"
                        + "<p class='id1'>}],<br/>"
                        + "<p class='id1'>" + quote + "solicitacoes" + quote + " : [{ </p>"
                        + "<p class='id2'>" + quote + "id" + quote + " : " + quote + r.get(0).getId() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "professor" + quote + " : " + quote + r.get(0).getPessoa().getNomeCompleto().substring(0, r.get(0).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(0).getPessoa().getNomeCompleto().substring(r.get(0).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "</p>"
                        + "<p class='id1'>}, {</p>"
                        + "<p class='id2'>" + quote + "id" + quote + " : " + quote + r.get(1).getId() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "professor" + quote + " : " + quote + r.get(1).getPessoa().getNomeCompleto().substring(0, r.get(1).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(1).getPessoa().getNomeCompleto().substring(r.get(1).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "</p>"
                        + "<p class='id1'>}, {</p>"
                        + "<p class='id2'>" + quote + "id" + quote + " : " + quote + r.get(2).getId() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "professor" + quote + " : " + quote + r.get(2).getPessoa().getNomeCompleto().substring(0, r.get(2).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(2).getPessoa().getNomeCompleto().substring(r.get(2).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "</p>"
                        + "<p class='id1'>}, {</p>"
                        + "<p class='id2'>" + quote + "id" + quote + " : " + quote + r.get(3).getId() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "professor" + quote + " : " + quote + r.get(3).getPessoa().getNomeCompleto().substring(0, r.get(3).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(3).getPessoa().getNomeCompleto().substring(r.get(3).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "</p>"
                        + "<p class='id1'>}, {</p>"
                        + "<p class='id2'>" + quote + "id" + quote + " : " + quote + r.get(4).getId() + quote + ",</p>"
                        + "<p class='id2'>" + quote + "professor" + quote + " : " + quote + r.get(4).getPessoa().getNomeCompleto().substring(0, r.get(4).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(4).getPessoa().getNomeCompleto().substring(r.get(4).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote + "</p>"
                        + "<p class='id1'>}]</p>"
                        + "<p class='id0'>}</p>"
                );
            } else if (acao.equals("teste2")) {
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

                ReservaDAO rdao = new ReservaDAO();
                LaboratorioDAO ldao = new LaboratorioDAO();
                EquipamentoDAO edao = new EquipamentoDAO();

                ArrayList<Reserva> r = rdao.selectSolicitacaoProf();

                for (Reserva i : r) {
                    i.getPessoa().setNomeCompleto(ad.getCN(i.getPessoa()));
                }

                out.println(util.Json.toJson(r));
            } else if (acao.equals("teste3")) {
                ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

                ReservaDAO rdao = new ReservaDAO();
                LaboratorioDAO ldao = new LaboratorioDAO();
                EquipamentoDAO edao = new EquipamentoDAO();

                ArrayList<Reserva> r = rdao.selectSolicitacaoProf();
                char quote = '"';

                for (Reserva i : r) {
                    i.getPessoa().setNomeCompleto(ad.getCN(i.getPessoa()));
                }

                String json = "{" + quote + "counter" + quote + " : [{"
                        + quote + "solicitacoes" + quote + " : " + quote + rdao.countSolicitacoes() + quote + ","
                        + quote + "reservas" + quote + " : " + quote + rdao.qtdReservas() + quote + ","
                        + quote + "reservas_hoje" + quote + " : " + quote + rdao.qtdReservasDia() + quote + ","
                        + quote + "laboratorios" + quote + " : " + quote + ldao.qtdLabs() + quote + ","
                        + quote + "computadores" + quote + " : " + quote + edao.qtdEquip() + quote
                        + "}],"
                        + quote + "solicitacoes" + quote + " : [{"
                        + quote + "id" + quote + " : " + quote + r.get(0).getId() + quote + ","
                        + quote + "professor" + quote + " : " + quote + r.get(0).getPessoa().getNomeCompleto().substring(0, r.get(0).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(0).getPessoa().getNomeCompleto().substring(r.get(0).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote
                        + "}, {"
                        + quote + "id" + quote + " : " + quote + r.get(1).getId() + quote + ","
                        + quote + "professor" + quote + " : " + quote + r.get(1).getPessoa().getNomeCompleto().substring(0, r.get(1).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(1).getPessoa().getNomeCompleto().substring(r.get(1).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote
                        + "}, {"
                        + quote + "id" + quote + " : " + quote + r.get(2).getId() + quote + ","
                        + quote + "professor" + quote + " : " + quote + r.get(2).getPessoa().getNomeCompleto().substring(0, r.get(2).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(2).getPessoa().getNomeCompleto().substring(r.get(2).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote
                        + "}, {"
                        + quote + "id" + quote + " : " + quote + r.get(3).getId() + quote + ","
                        + quote + "professor" + quote + " : " + quote + r.get(3).getPessoa().getNomeCompleto().substring(0, r.get(3).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(3).getPessoa().getNomeCompleto().substring(r.get(3).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote
                        + "}, {"
                        + quote + "id" + quote + " : " + quote + r.get(4).getId() + quote + ","
                        + quote + "professor" + quote + " : " + quote + r.get(4).getPessoa().getNomeCompleto().substring(0, r.get(4).getPessoa().getNomeCompleto().indexOf(" ")) + r.get(4).getPessoa().getNomeCompleto().substring(r.get(4).getPessoa().getNomeCompleto().lastIndexOf(" ")) + quote
                        + "}]"
                        + "}";

                out.println(util.Json.toJson(json));
            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
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
