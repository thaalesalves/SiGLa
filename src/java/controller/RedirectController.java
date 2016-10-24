// <editor-fold defaultstate="collapsed" desc="Pacotes & Importações">
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// </editor-fold>

public class RedirectController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        try {
            // <editor-fold defaultstate="collapsed" desc="Solicitação de reserva.">
            if (acao.equals("solicitar-reserva")) {
                request.getRequestDispatcher("labinfo/reserva.jsp").forward(request, response);
            } // </editor-fold> 
            // <editor-fold defaultstate="collapsed" desc="Listagem de reserva vazia.">
            else if (acao.equals("listar-reserva")) {
                request.getRequestDispatcher("/AlmightyController?acao=Reserva").forward(request, response);
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Listagem de reserva vazia.">
            else if (acao.equals("lista-populada")) {
                request.getRequestDispatcher("labinfo/reserva-listar.jsp").forward(request, response);
            } // </editor-fold>
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
