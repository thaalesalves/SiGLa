package controller.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DatabaseConnection;
import util.Logger;
import util.SiGLa;

public class DatabaseAtualizarAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            String database = request.getParameter("db-name");
            String user = request.getParameter("db-user");
            String passwd = request.getParameter("db-passwd");
            String addr = request.getParameter("db-host");

            SiGLa.writeProperty("sigla.db.name", database);
            SiGLa.writeProperty("sigla.db.user", user);
            SiGLa.writeProperty("sigla.db.passwd", passwd);
            SiGLa.writeProperty("sigla.db.addr", addr);
            
            Connection conn = DatabaseConnection.getConnection();
            DatabaseConnection.checkDatabase(conn);
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());

            session.setAttribute("msg", "Erro ao atualizar as informações");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/admin/database";
        }

        session.setAttribute("msg", "Banco de dados atualizado");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/admin/database";
    }
}
