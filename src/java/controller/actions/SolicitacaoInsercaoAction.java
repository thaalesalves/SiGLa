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
import dao.SoftwareDAO;
import dao.SolicitacaoDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailsender.Mail;
import mailsender.SolicitacaoMail;
import model.Pessoa;
import model.Software;
import model.Solicitacao;

public class SolicitacaoInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Mail mail = new SolicitacaoMail();
            boolean isEmpty = (request.getParameter("obs").trim().isEmpty() || request.getParameter("obs").trim() == null);
            
            CursoDAO cdao = new CursoDAO();
            SoftwareDAO sdao = new SoftwareDAO();
            SolicitacaoDAO dao = new SolicitacaoDAO();
            Solicitacao s = new Solicitacao();
            String[] softwares = request.getParameterValues("softwares");

            s.setPessoa((Pessoa) session.getAttribute("pessoa"));
            s.setTurma(request.getParameter("turma"));
            s.setQtdAlunos(Integer.parseInt(request.getParameter("qtd")));
            s.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
            s.setDiaSemana(request.getParameter("dia-semana").trim());
            s.setModulo(request.getParameter("modulo").trim());            
            s.setSoftwares(sdao.selectSoftwareAux(s));
            s.setCurso(cdao.selectId(s.getCurso()));
            
            for (String i : softwares) {
                Software sw = new Software();
                sw.setId(Integer.parseInt(i.trim()));
                s.getSoftwares().add(sw);
            }
            
            if (isEmpty) {
                s.setObservacao("Não informado.");
            } else {
                s.setObservacao(request.getParameter("obs").trim());
            }
            
            s = dao.insertSolicitacoes(s);
            
            for (Software i : s.getSoftwares()) {
                i = sdao.selectId(i);
            }            
            
            mail.setPessoa(s.getPessoa());
            mail.setSolicitacao(s);
            mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao efetivar a solicitação.");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/reserva/novo";
        }
        session.setAttribute("msg", "Solicitação efetuada com sucesso.");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/reserva/novo";
    }

}
