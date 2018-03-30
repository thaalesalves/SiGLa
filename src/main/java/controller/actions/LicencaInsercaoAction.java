/*
 * Copyright (C) 2018 thales
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package controller.actions;

import dao.DAOFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Licenca;
import model.Pessoa;
import model.Software;
import util.Logger;

public class LicencaInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Licenca licenca = new Licenca();
        licenca.setSoftware(new Software());
        
        try {
            DAOFactory fac = DAOFactory.getFactory();
            licenca.getSoftware().setId(Integer.parseInt(request.getParameter("licenca-software")));
            licenca.setDataVencimento(getData(request.getParameter("vencimento")));
            licenca.setDataAquisicao(getData(request.getParameter("aquisicao")));
            fac.getLicencaDAO().insert(licenca);
        } catch (Exception e) {
            session.setAttribute("msg", "Erro ao inserir licença.");
        session.setAttribute("status", "error");
            Logger.logSevere(e, LicencaInsercaoAction.class);
        }
        
        session.setAttribute("msg", "Licença inserida com sucesso.");
        session.setAttribute("status", "success");
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") inseriu uma licença no software #" + licenca.getSoftware().getId() + ".");
        return request.getContextPath() + "/licenca/novo";
    }

    public static Date getData(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(data);
    }
}
