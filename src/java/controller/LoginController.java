package controller;

import model.*;
import activedirectory.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String acao = request.getParameter("acao");
            if (acao == null || acao.equals("Entrar")) {
                ActiveDirectory ad = new ActiveDirectory();
                Pessoa p = new Pessoa();

                p.setUsername(request.getParameter("username")); // passa o atributo de usuário
                p.setSenha(request.getParameter("password")); // passa o atributo de senha

                if (ad.login(p)) { // faz o login
                    p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                    p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo
                    p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email
                    String[] groups = {"labinfo_prof", "labinfo_coord", "labinfo_admin", "labinfo_est", "labinfo_func", "DEPTI"};
                    for (String g : groups) {
                        if (ad.isMember(p, g)) {
                            switch (g) {
                                case "labinfo_prof":
                                    p.setCargo("professor");
                                    break;
                                case "labinfo_coord":
                                    p.setCargo("coordenador");
                                    break;
                                case "labinfo_admin":
                                    p.setCargo("administrador");
                                    break;
                                case "labinfo_est":
                                    p.setCargo("estagiário");
                                    break;
                                case "labinfo_func":
                                    p.setCargo("funcionário");
                                    break;
                                case "DEPTI":
                                    p.setCargo("TESTE");
                                    break;
                                default:
                                    request.setAttribute("login", "acesso");
                                    request.getRequestDispatcher("/index.jsp").forward(request, response); // chama de volta a página de login
                                    break;
                            }
                        }
                    }

                    request.getSession().setAttribute("pessoa", p); // salva dados do login na sessão
                    request.getRequestDispatcher("/labinfo/index.jsp").forward(request, response); // chama o index do SiGLa
                } else { // caso o usuário não exista
                    request.setAttribute("login", "false");
                    request.getRequestDispatcher("/index.jsp").forward(request, response); // chama de volta a página de login
                }
            } else if (acao.equals("logout")) { // ação de logout do sistema
                request.getSession().invalidate(); // invalida a sessão
                response.sendRedirect(request.getContextPath() + "/index.jsp"); // volta para o login
            }
        } catch (Exception e) {
            e.printStackTrace();
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
