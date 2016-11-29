package controller.actions;

import dao.ReservaDAO;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;

public class ReservaPontualAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            Pessoa p = new Pessoa();
            ReservaDAO dao = new ReservaDAO();

        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }

        return "";
    }
}
