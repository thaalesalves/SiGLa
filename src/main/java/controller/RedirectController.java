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
// <editor-fold defaultstate="collapsed" desc="Pacotes & Importações">
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// </editor-fold>

public class RedirectController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String acao = request.getRequestURI().replace(request.getContextPath(), "");
        Map<String, String> mapaUrl = new HashMap<String, String>();
        RequestDispatcher rd;

        try {
            /* Redirecionamentos de /admin/ */
            mapaUrl.put("/admin", "/admin/install");
            mapaUrl.put("/install", "/admin/install");
            mapaUrl.put("/admin/install", "../labinfo/admin/install.jsp");
            mapaUrl.put("/admin/database", "../labinfo/admin/db.jsp");
            mapaUrl.put("/admin/activedirectory", "../labinfo/admin/ad.jsp");
            
            /* Redirecionamentos de /controle/ */
            mapaUrl.put("/controle/listar-labs", "../AlmightyController?acao=LaboratorioListagem");
            mapaUrl.put("/controle/listar-softwares", "../AlmightyController?acao=SoftwareListagem");
            mapaUrl.put("/controle/contar-solicitacoes", "../CounterController");
            mapaUrl.put("/controle/listar-reservas", "../AlmightyController?acao=ReservaListagem");
            mapaUrl.put("/controle/listar-reservas-hoje", "../AlmightyController?acao=ReservaDiaListagem");
            mapaUrl.put("/controle/listar-solicitacoes", "../AlmightyController?acao=SolicitacaoListagem");
            mapaUrl.put("/controle/listar-curso", "../AlmightyController?acao=CursoListagem");
            mapaUrl.put("/controle/nova-reserva", "../AlmightyController?acao=ReservaInsercao");
            
            /* Redirecionamentos de /pagina/ */
            mapaUrl.put("/pagina/login", "../index.jsp");
            mapaUrl.put("/pagina/logout", "../AlmightyController?acao=Logout");
            mapaUrl.put("/pagina/home", "../labinfo/index.jsp");

            /* Redirecionamentos de /reserva/ */            
            mapaUrl.put("/reserva/novo", "../labinfo/reserva/novo.jsp");
            mapaUrl.put("/reserva/lista", "../labinfo/reserva/lista.jsp");
            mapaUrl.put("/reserva/hoje", "../labinfo/reserva/lista_dia.jsp");
            mapaUrl.put("/reserva/solicitacoes", "../labinfo/reserva/solicitacoes.jsp");
            
            /* Redirecionamentos de /curso/ */
            mapaUrl.put("/curso/novo", "../labinfo/curso/novo.jsp");
            mapaUrl.put("/curso/lista", "../labinfo/curso/lista.jsp");

            /* Redirecionamentos de /error/ */
            mapaUrl.put("/error/error", "../labinfo/error/500_exception.jsp");
            mapaUrl.put("/error/500", "../labinfo/error/500.jsp");
            mapaUrl.put("/error/404", "../labinfo/error/404.jsp");
            mapaUrl.put("/error/403", "../labinfo/error/403.jsp");
            mapaUrl.put("/error/401", "../labinfo/error/401.jsp");

            /* Redirecionamentos de /software/ */
            mapaUrl.put("/software/novo", "../labinfo/software/novo.jsp");
            mapaUrl.put("/software/lista", "../labinfo/software/lista.jsp");
            
            /* Redirecionamentos de /lab/ */
            mapaUrl.put("/laboratorio/novo", "../labinfo/lab/novo.jsp");
            mapaUrl.put("/laboratorio/lista", "../labinfo/lab/lista.jsp");
            
            if ((rd = request.getRequestDispatcher(mapaUrl.get(acao))) != null) {
                rd.forward(request, response);
            } else {
                request.getRequestDispatcher("/labinfo/error/404.jsp").forward(request, response);
            }
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: doPost(HttpServletRequest, HttpServletResponse)">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: doGet(HttpServletRequest, HttpServletResponse)">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos padrão: getServletInfo()">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
