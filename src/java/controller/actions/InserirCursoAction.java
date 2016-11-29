package controller.actions;

import dao.CursoDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Curso;

public class InserirCursoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            HttpSession session = request.getSession();

            CursoDAO dao = new CursoDAO();
            Curso c = new Curso();

            c.setNome(request.getParameter("curso"));
            c.setModalidade(request.getParameter("modalidade"));

            dao.insert(c);
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }
        return request.getContextPath() + "/curso/novo";
    }
}
