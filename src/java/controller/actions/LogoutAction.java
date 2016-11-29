package controller.actions;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            request.getSession().invalidate(); // invalida a sessão
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }

        return request.getContextPath();
    }
}
