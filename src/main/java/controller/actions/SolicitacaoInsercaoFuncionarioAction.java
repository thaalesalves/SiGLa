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

import dao.CursoDAO;
import dao.ReservaDAO;
import dao.SoftwareDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailsender.Mail;
import mailsender.SolicitacaoMail;
import model.Modulo;
import model.Reserva;
import model.Software;
import util.ActiveDirectory;

public class SolicitacaoInsercaoFuncionarioAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Mail mail = new SolicitacaoMail();
            boolean isEmpty = (request.getParameter("obs").trim().isEmpty() || request.getParameter("obs").trim() == null);

            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

            CursoDAO cdao = new CursoDAO();
            SoftwareDAO sdao = new SoftwareDAO();
            ReservaDAO dao = new ReservaDAO();
            Reserva r = new Reserva();
            String[] modulos = request.getParameterValues("modulo");
            String[] softwares = request.getParameterValues("softwares");
            r.getPessoa().setUsername(request.getParameter("email").replaceAll("\n", "").replaceAll("\r", ""));
            r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));
            r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
            r.getPessoa().setShownName(r.getPessoa().getNome() + " " + r.getPessoa().getNomeCompleto().substring(r.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
            r.getPessoa().setEmail(r.getPessoa().getUsername() + "@sigla.thalesalv.es");
            r.setTurma(request.getParameter("turma").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
            r.setQtdAlunos(Integer.parseInt(request.getParameter("qtd")));
            r.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
            r.setDiaDaSemana(request.getParameter("dia-semana").trim());
            r.setCurso(cdao.selectId(r.getCurso()));
            r.getLab().setId(Integer.parseInt(request.getParameter("laboratorio")));

            for (String i : modulos) {
                Modulo m = new Modulo();
                m.setId(Integer.parseInt(i.trim()));
                r.getModulos().add(m);
            }

            for (String i : softwares) {
                Software sw = new Software();
                sw.setId(Integer.parseInt(i.trim()));
                r.getSoftwares().add(sw);
            }

            if (isEmpty) {
                r.setObservacao("Nada informado.");
            } else {
                r.setObservacao(request.getParameter("obs").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
            }

            r = dao.insert(r);

            mail.setPessoa(r.getPessoa());
            mail.setReserva(r);
            //mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao efetivar a reserva.");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/reserva/novo";
        }
        session.setAttribute("msg", "Reserva efetuada com sucesso.");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/reserva/novo";
    }

}
