package controller.actions;

import model.*;
import dao.*;
import activedirectory.ActiveDirectory;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaPontualAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            Pessoa p = new Pessoa();
            ReservaDAO dao = new ReservaDAO();

        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return "";
    }
}
