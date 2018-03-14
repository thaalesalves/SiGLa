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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import util.ActiveDirectory;
import util.Logger;

public class SolicitacaoAprovacaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Solicitacao s = new Solicitacao();
        Reserva r = new Reserva();

        try {
            DAOFactory fac = DAOFactory.getFactory();
            Mail mail = new SolicitacaoAprovacaoMail();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");

            s.setId(Integer.parseInt(request.getParameter("solicitacao")));
            s = fac.getSolicitacaoDAO().selectSolicitacao(s);

            r.getLab().setId(Integer.parseInt(request.getParameter("laboratorio")));
            r.setPessoa(s.getPessoa());
            r.setCurso(s.getCurso());
            r.setDiaDaSemana(s.getDiaSemana());
            r.setModulos(s.getModulos());
            r.setObservacao(s.getObservacao());
            r.setQtdAlunos(s.getQtdAlunos());
            r.setTurma(s.getTurma());
            r.setSoftwares(s.getSoftwares());
            r = fac.getReservaDAO().insert(r);

            Pessoa p = r.getPessoa();
            p.setEmail(ad.getMail(p));
            p.setNome(ad.getGivenName(p));
            p.setNomeCompleto(ad.getCN(p));

            fac.getSolicitacaoDAO().deleteSolicitacao(s);
            mail.setPessoa(p);
            mail.setReserva(r);
            mail.setSolicitacao(s);
            mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());

            session.setAttribute("msg", "Erro ao efetivar a solicitação");
            session.setAttribute("status", "error");

            return request.getContextPath() + "/controle/solicitacoes";
        }
        session.setAttribute("msg", "Reserva efetivada com sucesso");
        session.setAttribute("status", "success");
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        Logger.logOutput(u.getNome() + " (" + u.getUsername() + ") aprovou a solitação #" 
                + s.getId() + ", e a reserva #" + r.getId() + " foi criada.");
        return request.getContextPath() + "/reserva/solicitacoes";
    }
}
