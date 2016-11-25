package controller.actions;

import util.ActiveDirectory;
import dao.ReservaDAO;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Reserva;

public class ReservaAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            ReservaDAO dao = new ReservaDAO();
            Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");
            Reserva r = new Reserva();
            r.setPessoa(p);
            ArrayList<Reserva> reserva = new ArrayList<Reserva>();
            ActiveDirectory ad = new ActiveDirectory();
            ad.login(p);
            dao.setAd(ad);

            if (p.getCargo().equals("Professor")) {
                reserva = dao.selectReserva(r); // chama reserva de professor
            } else {
                reserva = dao.selectReserva(); // chama reservas gerais
            }
            
            request.getSession().setAttribute("reserva", reserva);
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return "lista";
    }
}
