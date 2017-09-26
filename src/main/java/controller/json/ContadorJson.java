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
package controller.json;

import dao.DAOFactory;
import dao.dao.CursoDAO;
import dao.dao.EquipamentoDAO;
import dao.dao.LaboratorioDAO;
import dao.dao.ReservaDAO;
import dao.dao.SoftwareDAO;
import dao.dao.SolicitacaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Counter;
import model.Curso;
import model.Laboratorio;
import model.Software;
import model.Solicitacao;
import util.ActiveDirectory;

public class ContadorJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        try {
            HttpSession session = request.getSession();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

            Counter counter = new Counter();
            DAOFactory fac = DAOFactory.getFactory();

            ArrayList<Software> sw = fac.getSoftwareDAO().selectAll();
            ArrayList<Curso> c = fac.getCursoDAO().selectAll();
            ArrayList<Solicitacao> r = fac.getSolicitacaoDAO().selectSolicitacao();
            ArrayList<Laboratorio> l = fac.getLaboratorioDAO().selectLaboratorios();

            for (Solicitacao i : r) {
                i.getPessoa().setNomeCompleto(ad.getCN(i.getPessoa()));
                i.getPessoa().setNome(ad.getGivenName(i.getPessoa()));
                i.getPessoa().setShownName(i.getPessoa().getNome() + " " + i.getPessoa().getNomeCompleto().substring(i.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
            }

            counter.setQtdComputadores(fac.getEquipamentoDAO().qtdEquip());
            counter.setQtdLaboratorios(fac.getLaboratorioDAO().qtdLabs());
            counter.setQtdReservas(fac.getReservaDAO().qtdReservas());
            counter.setQtdReservasHoje(fac.getReservaDAO().qtdReservasDia());
            counter.setQtdSolicitacoes(fac.getSolicitacaoDAO().countSolicitacoes());
            counter.setSolicitacoes(r);
            counter.setLaboratorios(l);
            counter.setCursos(c);
            counter.setSoftwares(sw);

            return util.Json.toCuteJson(counter);
        } catch (Exception e) {
            util.Logger.logSevere(e, ContadorJson.class);
        }

        return "";
    }
}
