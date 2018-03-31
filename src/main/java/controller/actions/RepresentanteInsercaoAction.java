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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Fornecedor;
import model.Pessoa;
import model.Representante;
import util.Logger;

public class RepresentanteInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        
        try {
            DAOFactory fac = DAOFactory.getFactory();
            Representante rep = new Representante();
            rep.setNome(request.getParameter("rep-name"));
            rep.setTelefone(request.getParameter("rep-tel"));
            rep.setEmail(request.getParameter("rep-email"));
            rep.setFornecedor(new Fornecedor());
            rep.getFornecedor().setId(Integer.parseInt(request.getParameter("forn-id")));
            fac.getRepresentanteDAO().insert(rep);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            
            session.setAttribute("msg", "Erro ao cadastrar o representante");
            session.setAttribute("status", "error");
            
            return request.getContextPath() + "/fornecedor/lista";
        }
        
        session.setAttribute("msg", "Representante cadastrado com sucesso");
        session.setAttribute("status", "success");
        
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") cadastrou um representante.");
        return request.getContextPath() + "/fornecedor/lista";
    }

}
