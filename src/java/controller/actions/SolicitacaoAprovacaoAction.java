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

import dao.ReservaDAO;
import dao.SolicitacaoDAO;
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
import mailsender.SolicitacaoAprovacaoMail;
import model.Pessoa;
import model.Reserva;
import model.Solicitacao;

public class SolicitacaoAprovacaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();

        try {
            Solicitacao s = new Solicitacao();
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            Reserva r = new Reserva();
            ReservaDAO rdao = new ReservaDAO();
            Mail mail = new SolicitacaoAprovacaoMail();

            s.setId(Integer.parseInt(request.getParameter("solicitacao")));
            s = sdao.selectSolicitacao(s);

            r.getLab().setId(Integer.parseInt(request.getParameter("laboratorio")));
            r.setPessoa(s.getPessoa());
            r.setCurso(s.getCurso());
            r.setSoftware(s.getSoftware());
            r.setDiaDaSemana(s.getDiaSemana());
            r.setModulo(s.getModulo());
            r.setObservacao(s.getObservacao());
            r.setQtdAlunos(s.getQtdAlunos());
            r.setTurma(s.getTurma());
            r = rdao.insert(r);
            
            sdao.deleteSolicitacao(s);
            mail.setPessoa((Pessoa) session.getAttribute("pessoa"));
            mail.setReserva(r);
            mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());

            session.setAttribute("msg", "Erro ao efetivar a solicitação");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/controle/listar-solicitacoes";
        }
        session.setAttribute("msg", "Reserva efetivada com sucesso");
        session.setAttribute("status", "success");

        return request.getContextPath() + "/controle/listar-solicitacoes";
    }
}
