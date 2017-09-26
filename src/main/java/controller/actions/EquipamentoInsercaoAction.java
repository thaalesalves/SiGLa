/*
 * Copyright (C) 2017 thaal
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
import dao.dao.EquipamentoDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipamento;
import model.Laboratorio;

public class EquipamentoInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Equipamento e = new Equipamento();
            DAOFactory fac = DAOFactory.getFactory();;
            e.setLab(new Laboratorio());
            
            e.setNome(request.getParameter("netbios"));
            e.setIp(request.getParameter("ip"));
            e.setMac(request.getParameter("mac"));
            e.setConfig(request.getParameter("config"));            
            e.getLab().setId(Integer.parseInt(request.getParameter("laboratorio")));
            
            fac.getEquipamentoDAO().insert(e);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            util.Logger.logSevere(e, EquipamentoInsercaoAction.class);
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao cadastrar computador");

            return request.getContextPath() + "/equip/novo";
        }

        session.setAttribute("status", "error");
        session.setAttribute("msg", "Erro ao cadastrar computador");

        return request.getContextPath() + "/equip/novo";
    }
}
