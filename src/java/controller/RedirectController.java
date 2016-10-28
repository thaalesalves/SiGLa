// <editor-fold defaultstate="collapsed" desc="Pacotes & Importações">
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        try {
            // <editor-fold defaultstate="collapsed" desc="Procedimento por máscara">
            mapaUrl.put("/pagina/login", "../index.jsp");
            mapaUrl.put("/pagina/logout", "../AlmightyController?acao=Logout");
            mapaUrl.put("/pagina/home", "../labinfo/index.jsp");
            mapaUrl.put("/reserva/novo", "../labinfo/reserva.jsp");
            mapaUrl.put("/reserva/listar", "../AlmightyController?acao=Reserva");
            mapaUrl.put("/reserva/lista", "../labinfo/reserva-listar.jsp");
            
            RequestDispatcher rd = request.getRequestDispatcher(mapaUrl.get(acao));
            
            if (rd != null) {
                rd.forward(request, response);
            } else {
                request.getRequestDispatcher("/error/404.jsp").forward(request, response);
            } // </editor-fold>
            
            // <editor-fold defaultstate="collapsed" desc="Procedimento tradicional">
            /*
            String acao = request.getParameter("acao");
            
            if (acao.equals("solicitar-reserva")) {
                request.getRequestDispatcher("labinfo/reserva.jsp").forward(request, response);
            }
            else if (acao.equals("listar-reserva")) {
                request.getRequestDispatcher("/AlmightyController?acao=Reserva").forward(request, response);
            }
            else if (acao.equals("lista-populada")) {
                request.getRequestDispatcher("labinfo/reserva-listar.jsp").forward(request, response);
            } */ // </editor-fold>
        } catch (Exception e) {
            e.printStackTrace();
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
