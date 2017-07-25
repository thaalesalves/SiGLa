package controller.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Logger;
import util.SiGLa;

public class DatabaseAtualizarAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            File cfg = new File(SiGLa.TARGET + "/classes/db.properties");
            Properties props = new Properties();
            FileWriter writer = new FileWriter(cfg);

            String domain = request.getParameter("dominio");
            String netbios = request.getParameter("netbios");
            String addr = request.getParameter("host");
            String auth = request.getParameter("auth");

            if (auth == null) {
                auth = "ldaps";
            }

            props.setProperty("sigla.auth.domain", domain);
            props.setProperty("sigla.auth.netbios", netbios);
            props.setProperty("sigla.auth.method", auth);
            props.setProperty("sigla.auth.host", addr);
            props.store(new FileOutputStream(cfg), null);
            //props.store(writer, null);

            writer.close();
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "Erro ao atualizar as informações");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/admin/activedirectory";
        }

        session.setAttribute("msg", "Informações de domínio atualizadas");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/admin/activedirectory";
    }
}
