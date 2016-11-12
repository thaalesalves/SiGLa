package controller.actions;

import java.io.IOException;
import java.net.ConnectException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ConnectException, IOException, NamingException, ServletException {
        request.getSession().invalidate(); // invalida a sessão
        return "login"; // volta para o login
    }
}
