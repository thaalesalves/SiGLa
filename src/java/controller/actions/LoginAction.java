package controller.actions;

import model.*;
import activedirectory.ActiveDirectory;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        try {
            // <editor-fold defaultstate="collapsed" desc="Atributos do método.">
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            Grupo groups = new Grupo();

            p.setUsername(request.getParameter("username")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            request.getSession().setAttribute("usuario-ativo", p.getUsername());
            request.getSession().setAttribute("senha-ativa", p.getSenha());
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Invocação do login.">
            if (ad.login(p)) { // faz o login
                p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo
                p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email
                //String[] groups = {"labinfo_prof", "labinfo_coord", "labinfo_admin", "labinfo_est", "labinfo_func", "DEPTI"};

                boolean acesso = false;
                for (String g : groups.getOu()) {
                    groups.setGrupo(g + groups.getDc());
                    if (ad.isMember(p, groups)) {
                        acesso = true;
                    }
                }

                if (acesso) {
                    request.getSession().setAttribute("pessoa", p); // salva dados do login na sessão
                    //return "/labinfo/index.jsp"; // chama o index do SiGLa
                    return "pagina/home";
                } else {
                    request.setAttribute("login", "acesso");
                    return "pagina/login"; // chama de volta a página de login
                }
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Login sem sucesso.">
            else { // caso o usuário não exista
                request.setAttribute("login", "false");
                return "/index.jsp"; // chama de volta a página de login
            } // </editor-fold>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
