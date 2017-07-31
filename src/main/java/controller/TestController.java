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

import dao.GrupoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grupo;
import util.SiGLa;

/**
 *
 * @author thaal
 */
public class TestController extends HttpServlet {

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
        String acao = request.getParameter("acao");

        if (acao.equals("install")) {
            try {
                Grupo g;
                GrupoDAO gdao = new GrupoDAO();
                ArrayList<Grupo> ag = new ArrayList<Grupo>();

                /* Dados do Banco */
                String dbDbms = request.getParameter("db-dbms");
                String dbName = request.getParameter("db-name");
                String dbHost = request.getParameter("db-host");
                String dbUser = request.getParameter("db-user");
                String dbPasswd = request.getParameter("db-passwd");

                /* Dados do Domínio */
                String adAuth = request.getParameter("ad-auth");
                String adDomain = request.getParameter("ad-domain");
                String adNetbios = request.getParameter("ad-netbios");
                String adController = request.getParameter("ad-controller");

                /* Processando Propriedades */
                SiGLa.writeProperty("sigla.db.name", dbName);
                SiGLa.writeProperty("sigla.db.user", dbUser);
                SiGLa.writeProperty("sigla.db.passwd", dbPasswd);
                SiGLa.writeProperty("sigla.db.addr", dbHost);
                SiGLa.writeProperty("sigla.db.dbms", dbDbms);

                SiGLa.writeProperty("sigla.auth.domain", adDomain);
                SiGLa.writeProperty("sigla.auth.netbios", adNetbios);
                SiGLa.writeProperty("sigla.auth.method", adAuth);
                SiGLa.writeProperty("sigla.auth.host", adController);

                util.DatabaseConnection.checkDatabase();

                /* Dados de Acesso */
                g = new Grupo();
                g.setRole("admin");
                g.setGrupo(request.getParameter("ldap-admin"));
                ag.add(g);

                g = new Grupo();
                g.setRole("funcionario");
                g.setGrupo(request.getParameter("ldap-func"));
                ag.add(g);

                g = new Grupo();
                g.setRole("estagiario");
                g.setGrupo(request.getParameter("ldap-est"));
                ag.add(g);

                g = new Grupo();
                g.setRole("coordenador");
                g.setGrupo(request.getParameter("ldap-coord"));
                ag.add(g);

                g = new Grupo();
                g.setRole("professor");
                g.setGrupo(request.getParameter("ldap-prof"));
                ag.add(g);

                for (Grupo i : ag) {
                    gdao.insert(i);
                }

                /* Resposta do Servidor */
                response.getWriter().write("Chamada AJAX completada");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(e.getMessage());
                response.flushBuffer();
                util.Logger.logSevere(e, e.getClass());
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
