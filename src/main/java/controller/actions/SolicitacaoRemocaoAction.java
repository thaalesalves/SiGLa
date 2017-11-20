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
import mailsender.Mail;
import mailsender.SolicitacaoReprovacaoMail;
import model.Solicitacao;
import util.ActiveDirectory;

public class SolicitacaoRemocaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            Mail mail = new SolicitacaoReprovacaoMail();
            Solicitacao s = new Solicitacao();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
            DAOFactory fac = DAOFactory.getFactory();
            
            String id = request.getParameter("solicitacao_id").trim();
            s.setId(Integer.parseInt(id));

            s = fac.getSolicitacaoDAO().selectSolicitacao(s);
            fac.getSolicitacaoDAO().deleteSolicitacao(s);
            
            s.getPessoa().setEmail(ad.getMail(s.getPessoa()));
            s.getPessoa().setNome(ad.getGivenName(s.getPessoa()));
            s.getPessoa().setNomeCompleto(ad.getCN(s.getPessoa()));
            s.getPessoa().setShownName(ad.getDisplayName(s.getPessoa()));
            
            mail.setPessoa(s.getPessoa());
            mail.setSolicitacao(s);
            mail.sendMail(mail);
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            
            session.setAttribute("msg", "Erro ao reprovar solicitação");
            session.setAttribute("status", "error");
            
            return request.getContextPath() + "/controle/listar-solicitacoes";
        }
        
        session.setAttribute("msg", "Reserva reprovada");
        session.setAttribute("status", "success");        
        
        return request.getContextPath() + "/reserva/solicitacoes";
    }
}
