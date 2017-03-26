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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailsender.Mail;
import mailsender.MailSolicitacao;
import model.Pessoa;
import model.Solicitacao;

public class SolicitacaoInsercaoAction implements ICommand {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        
        try {
            Mail mail = new MailSolicitacao();
            
            CursoDAO cdao = new CursoDAO();
            SoftwareDAO sdao = new SoftwareDAO();
            SolicitacaoDAO dao = new SolicitacaoDAO();
            Solicitacao s = new Solicitacao();
            
            s.setPessoa((Pessoa) session.getAttribute("pessoa"));
            s.setModulo(request.getParameter("modulo"));
            s.setTurma(request.getParameter("turma"));
            s.setQtdAlunos(Integer.parseInt(request.getParameter("qtd")));
            s.getSoftware().setId(Integer.parseInt(request.getParameter("softwares").trim()));
            s.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
            s.setDiaSemana(request.getParameter("dia-semana").trim());
            
            if (request.getParameter("obs") != null) {
                s.setObservacao(request.getParameter("obs"));
            } else {
                s.setObservacao("Sem observa&ccedil;&otilde;es.");
            }
            
            s.setSoftware(sdao.selectId(s.getSoftware()));
            s.setCurso(cdao.selectId(s.getCurso()));

            mail.setSolicitacao(s);
            mail.sendMail(mail);
            dao.insertSolicitacao(s);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            session.setAttribute("msg", "Erro ao efetivar a solicita&ccedil;&atilde;o.");
            return request.getContextPath() + "/reserva/novo";
        }
        session.setAttribute("msg", "Solicita&ccedil;&atilde;o efetuada com sucesso.");
        return request.getContextPath() + "/reserva/novo";
    }
    
}
