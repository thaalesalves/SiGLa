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

public class ListarReservaDiaAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();
            r.setPessoa((Pessoa) request.getSession().getAttribute("pessoa"));
            ArrayList<Reserva> reserva = new ArrayList<Reserva>();
            ActiveDirectory ad = new ActiveDirectory();
            ad.login(r.getPessoa());

            if (r.getPessoa().getCargo().equals("Professor")) {
                reserva = dao.selectReserva(r); // chama reserva de professor
            } else {
                reserva = dao.selectReserva(); // chama reservas gerais
            }

            for (Reserva res : reserva) {
                reserva.get(reserva.indexOf(res)).getPessoa().setNome(ad.getGivenName(reserva.get(reserva.indexOf(res)).getPessoa()));
                reserva.get(reserva.indexOf(res)).getPessoa().setNomeCompleto(ad.getCN(reserva.get(reserva.indexOf(res)).getPessoa()));
            }

            request.getSession().setAttribute("reserva", reserva);
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getName() + ": " + e.getMessage());
        }

        return "lista-dia";
    }
}
