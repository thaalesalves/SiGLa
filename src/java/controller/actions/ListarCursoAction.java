package controller.actions;

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

public class ListarCursoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        CursoDAO cdao = new CursoDAO();
        ArrayList<Curso> ac = cdao.selectAll();
        session.setAttribute("lista-cursos", ac);

        return request.getContextPath() + "/curso/lista";
    }

}
