package controller.commandfactory;

import dao.*;
import model.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaAction implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        Reserva r = new Reserva();
        ReservaDAO dao = new ReservaDAO();
        
        
        
        return "";
    }
}
