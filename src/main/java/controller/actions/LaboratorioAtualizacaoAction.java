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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Laboratorio;
import model.Pessoa;
import model.Software;
import util.Logger;

public class LaboratorioAtualizacaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Laboratorio lab = new Laboratorio();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();

            List<Software> softwares = new ArrayList<Software>();
            String[] sws = request.getParameterValues("modal_sw_lab");

            for (String i : sws) {
                Software sw = new Software();
                sw.setId(Integer.parseInt(i));
                softwares.add(sw);
            }

            lab.setId(Integer.parseInt(request.getParameter("modal_id_lab")));
            lab.setComputadores(Integer.parseInt(request.getParameter("modal_comps_lab")));
            lab.setCapacidade(Integer.parseInt(request.getParameter("modal_cap_lab")));
            lab.setSoftwares(softwares);

            fac.getLaboratorioDAO().atualizar(lab);
            lab = fac.getLaboratorioDAO().selectLaboratorio(lab);
        } catch (Exception e) {
            Logger.logSevere(e, EquipamentoInsercaoAction.class);
            session.setAttribute("status", "error");
            session.setAttribute("msg", "Erro ao atualizar laboratório");
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou "
                    + "atualizar o laboratório " + lab.getNumero() + "(#" + lab.getId() + ")");
            return request.getContextPath() + "/laboratorio/lista";
        }

        session.setAttribute("status", "success");
        session.setAttribute("msg", "Laboratório atualizado");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") alterou o laboratório " + lab.getNumero() + "(#" + lab.getId() + ").");
        return request.getContextPath() + "/laboratorio/lista";
    }
}
