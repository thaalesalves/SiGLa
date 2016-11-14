package controller.actions;

import dao.SoftwareDAO;
import dao.CursoDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Curso;
import model.Reserva;
import model.Software;

public class ListarReservaPontualAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        CursoDAO cdao = new CursoDAO();
        ArrayList<Curso> ac = cdao.selectAll();

        SoftwareDAO swdao = new SoftwareDAO();
        ArrayList<Software> asw = swdao.selectAll();

        Reserva r = new Reserva();
        r.setCursos(ac);
        r.setSoftwares(asw);

        session.setAttribute("dados-pontual", r);

        return request.getContextPath() + "/reserva/pontual";
    }

}
