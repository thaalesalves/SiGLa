package controller.actions;

import dao.ReservaDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import model.Reserva;

public class InserirReservaSemestralAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            HttpSession session = request.getSession();

            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();

            r.setPessoa((Pessoa) session.getAttribute("pessoa"));
            r.setDiaDaSemana(request.getParameter("dia-semana"));
            r.setModulo(request.getParameter("modulo"));
            r.setTurma(request.getParameter("turma"));
            r.setObservacao(request.getParameter("obs"));
            r.setQtd(Integer.parseInt(request.getParameter("qtd")));
            r.getSoftware().setId(Integer.parseInt(request.getParameter("softwares").trim()));
            r.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
            
            dao.insertSemestral(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request.getContextPath() + "/reserva/listar-semestral";
    }
}
