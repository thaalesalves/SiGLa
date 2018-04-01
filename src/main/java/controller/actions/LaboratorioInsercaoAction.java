/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
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
import model.Equipamento;
import model.Laboratorio;
import model.Pessoa;
import util.Logger;

public class LaboratorioInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Laboratorio l = new Laboratorio();
            DAOFactory fac = DAOFactory.getFactory();

            l.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
            l.setComputadores(Integer.parseInt(request.getParameter("computadores")));
            
            if (l.getComputadores() < 1 || l.getCapacidade() < 1) {
                session.setAttribute("mensagem", "Tentativa ilegal de passar valores.");
                session.setAttribute("estado", "error");
                return request.getContextPath() + "/laboratorio/novo";
            }
            
            l.setNumero(request.getParameter("numero"));
            l.setMemoria(request.getParameter("memoria"));
            l.setModelo(request.getParameter("modelo"));
            l.setProcessador(request.getParameter("processador"));

            fac.getLaboratorioDAO().insertLaboratorio(l);
            l = fac.getLaboratorioDAO().selectLaboratorioNumero(l);
            
            for (int i = 1; i < l.getComputadores() + 1; i++) {
                String formatted = String.format("%03d", i);
                Equipamento e = new Equipamento();
                e.setConfig(l.getModelo() + "; " + l.getProcessador() + "; " + l.getMemoria());
                e.setIp(request.getParameter("ip") + "." + i);
                e.setNome(l.getNumero().replace("-", "") + "LAB" + formatted);
                e.setStatus(1);
                e.setMac("Não informado.");
                e.setLab(l);
                fac.getEquipamentoDAO().insert(e);
            }
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            
            session.setAttribute("msg", "Erro ao cadastrar o laboratório");
            session.setAttribute("status", "error");
            
            return request.getContextPath() + "/laboratorio/novo";
        }
        session.setAttribute("msg", "Laboratório cadastrado com sucesso");
        session.setAttribute("status", "success");
        Pessoa u = (Pessoa) request.getSession().getAttribute("pessoa");
                Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") acaba de inserir um laboratório.");
        return request.getContextPath() + "/laboratorio/novo";
    }

}
