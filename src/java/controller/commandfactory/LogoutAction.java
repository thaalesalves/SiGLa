package controller.commandfactory;

import model.Pessoa;
import activedirectory.ActiveDirectory;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        request.getSession().invalidate(); // invalida a sessão
        return "/index.jsp"; // volta para o login
    }
}
