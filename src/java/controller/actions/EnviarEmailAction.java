package controller.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Mail;
import model.Pessoa;

public class EnviarEmailAction implements ICommand {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        try {
            HttpSession session = request.getSession();
            Mail mail = new Mail();
            Pessoa pessoa = (Pessoa) session.getAttribute("pessoa");
            
            mail.setPessoa(pessoa);
            mail.setDestinatario(request.getParameter("emailto"));
            mail.setSubject(request.getParameter("subject"));
            mail.setMensagem(request.getParameter("message"));
            
            mail.sendMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request.getContextPath() + "/pagina/home";
    }
}
