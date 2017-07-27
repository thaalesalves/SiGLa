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
import util.Logger;
import util.SiGLa;

public class ActiveDirectoryAtualizarAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            String dominio = request.getParameter("dominio");
            String netbios = request.getParameter("netbios");
            String addr = request.getParameter("host");
            String auth = request.getParameter("auth");

            SiGLa.writeProperty("sigla.auth.domain", dominio);
            SiGLa.writeProperty("sigla.auth.netbios", netbios);
            SiGLa.writeProperty("sigla.auth.method", addr);
            SiGLa.writeProperty("sigla.auth.host", auth);
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "Erro ao atualizar as informações");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/admin/activedirectory";
        }

        session.setAttribute("msg", "Informações de banco de dados atualizadas");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/admin/activedirectory";
    }
}
