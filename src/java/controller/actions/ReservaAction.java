package controller.actions;

import activedirectory.*;
import dao.*;
import model.*;
import java.util.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {
        try {
            ReservaDAO dao = new ReservaDAO();
            Pessoa p = new Pessoa();

            p.setUsername((String) request.getSession().getAttribute("usuario-ativo"));
            p.setSenha((String) request.getSession().getAttribute("senha-ativa"));

            ArrayList<Reserva> reserva = dao.selectReserva(p);
            
            request.getSession().setAttribute("reserva", reserva);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "lista";
    }
}
