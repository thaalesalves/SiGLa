package controller.actions;

import dao.CursoDAO;
import dao.ReservaDAO;
import dao.SoftwareDAO;
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
import util.Mail;

public class InserirReservaSemestralAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            Mail mail = new Mail();
            
            CursoDAO cdao = new CursoDAO();
            SoftwareDAO sdao = new SoftwareDAO();
            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();

            r.setPessoa((Pessoa) session.getAttribute("pessoa"));
            r.setDiaDaSemana(request.getParameter("dia-semana"));
            r.setModulo(request.getParameter("modulo"));
            r.setTurma(request.getParameter("turma"));
            r.setQtd(Integer.parseInt(request.getParameter("qtd")));                
            r.getSoftware().setId(Integer.parseInt(request.getParameter("softwares").trim()));
            r.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
            
            if (request.getParameter("obs") == null) {
                r.setObservacao("Nenhuma informada.");
            } else {
                r.setObservacao(request.getParameter("obs")); 
            }
            
            r.setSoftware(sdao.selectId(r.getSoftware()));
            r.setCurso(cdao.selectId(r.getCurso()));
            
            mail.sendEmailReservaSemestral(r.getPessoa(), r);
            
            dao.insertSemestral(r);
        } catch (Exception e) {
            System.err.println("Erro em " + this.getClass().getSimpleName() + ": " + e.getMessage());
            session.setAttribute("status", "error");
            return request.getContextPath() + "/reserva/listar-semestral";
        }
        session.setAttribute("status", "success");
        return request.getContextPath() + "/reserva/listar-semestral";
    }
}
