package controller.commandfactory;

import model.Pessoa;
import activedirectory.ActiveDirectory;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        try {
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();

            p.setUsername(request.getParameter("username")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            if (ad.login(p)) { // faz o login
                p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo
                p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email
                String[] groups = {"labinfo_prof", "labinfo_coord", "labinfo_admin", "labinfo_est", "labinfo_func", "DEPTI"};

                boolean acesso = false;
                for (String g : groups) {
                    if (ad.isMember(p, g)) {
                        acesso = true;
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
                        }
                    }
                }

                if (acesso) {
                    request.getSession().setAttribute("pessoa", p); // salva dados do login na sessão
                    return "/labinfo/index.jsp"; // chama o index do SiGLa
                } else {
                    request.setAttribute("login", "acesso");
                    return "/index.jsp"; // chama de volta a página de login
                }
            } else { // caso o usuário não exista
                request.setAttribute("login", "false");
                return "/index.jsp"; // chama de volta a página de login
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
