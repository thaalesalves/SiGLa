package controller.actions;

import activedirectory.*;
import dao.*;
import java.io.IOException;
import java.net.ConnectException;
import model.*;
import java.util.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ConnectException, IOException, NamingException, ServletException {
        try {
            ReservaDAO dao = new ReservaDAO();
            Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
            Reserva res = new Reserva();
            res.setPessoa(p);
            ArrayList<Reserva> reserva = new ArrayList<Reserva>();
            ActiveDirectory ad = new ActiveDirectory();
            ad.login(p);
            dao.setAd(ad);
            
            if (p.getCargo().equals("Professor")) {
                reserva = dao.selectReservaProfessor(p);
            } else {
                reserva = dao.selectReserva(res);
            }

            request.getSession().setAttribute("reserva", reserva);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "lista";
    }
}
