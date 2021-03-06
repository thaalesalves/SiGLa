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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailsender.Mail;
import mailsender.ReservaEquipeMail;
import mailsender.ReservaMail;
import mailsender.SolicitacaoMail;
import model.Modulo;
import model.Pessoa;
import model.Reserva;
import model.Software;
import model.Solicitacao;
import util.ActiveDirectory;
import util.Logger;

public class SolicitacaoInsercaoAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        Pessoa u = (Pessoa) session.getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
            String role = request.getParameter("role");
            boolean isEmpty = (request.getParameter("obs").trim().isEmpty() || request.getParameter("obs").trim() == null);

            if (role.equals("admin") || role.equals("funcionario")) {
                Mail mailProf = new ReservaMail();
                Mail mailFunc = new ReservaEquipeMail();
                Reserva r = new Reserva();

                String[] modulos = request.getParameterValues("modulo");
                String[] softwares = request.getParameterValues("softwares");

                r.getPessoa().setUsername(request.getParameter("professor").replaceAll("\n", "").replaceAll("\r", ""));
                r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));
                r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
                r.getPessoa().setShownName(r.getPessoa().getNome() + " " + r.getPessoa().getNomeCompleto().substring(r.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                r.getPessoa().setEmail(ad.getMail(r.getPessoa()).trim());
                r.setTurma(request.getParameter("turma").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
                r.setQtdAlunos(Integer.parseInt(request.getParameter("qtd-alunos")));

                if (r.getQtdAlunos() < 1) {
                    session.setAttribute("mensagem", "Tentativa ilegal de passar valores.");
                    session.setAttribute("estado", "error");
                     
                    return request.getContextPath() + "/reserva/novo";
                }

                r.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
                r.setDiaDaSemana(request.getParameter("dia-semana").trim());
                r.setCurso(fac.getCursoDAO().selectId(r.getCurso()));
                r.getLab().setId(Integer.parseInt(request.getParameter("laboratorio")));
                r.getPessoa().setEmail(ad.getMail(r.getPessoa()));
                r.getPessoa().setNome(ad.getGivenName(r.getPessoa()));
                r.getPessoa().setNomeCompleto(ad.getCN(r.getPessoa()));

                Pessoa p = (Pessoa) session.getAttribute("pessoa");

                for (String i : modulos) {
                    Modulo m = new Modulo();
                    m.setId(Integer.parseInt(i.trim()));
                    r.getModulos().add(m);
                }

                for (String i : softwares) {
                    Software sw = new Software();
                    sw.setId(Integer.parseInt(i.trim()));
                    sw = fac.getSoftwareDAO().selectId(sw);
                    r.getSoftwares().add(sw);
                }

                if (isEmpty) {
                    r.setObservacao("Nada informado.");
                } else {
                    r.setObservacao(request.getParameter("obs").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
                }

                r = fac.getReservaDAO().insert(r);

                mailFunc.setPessoa(p);
                mailFunc.setReserva(r);
                mailFunc.sendMail(mailFunc);

                r.getPessoa().setEmail(ad.getMail(r.getPessoa()));

                mailProf.setPessoa(p);
                mailProf.setReserva(r);
                mailProf.sendMail(mailProf);

                session.setAttribute("msg", "Reserva efetuada com sucesso.");
                session.setAttribute("status", "success");
                 
                return request.getContextPath() + "/reserva/novo";
            } else if (role.equals("coordenador") || role.equals("professor")) {
                Mail mail = new SolicitacaoMail();
                Solicitacao s = new Solicitacao();
                String[] modulos = request.getParameterValues("modulo");
                String[] softwares = request.getParameterValues("softwares");

                s.getPessoa().setUsername(request.getParameter("professor").replaceAll("\n", "").replaceAll("\r", ""));
                s.getPessoa().setNomeCompleto(ad.getCN(s.getPessoa()));
                s.getPessoa().setNome(ad.getGivenName(s.getPessoa()));
                s.getPessoa().setShownName(s.getPessoa().getNome() + " " + s.getPessoa().getNomeCompleto().substring(s.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
                s.getPessoa().setEmail(ad.getMail(s.getPessoa()).trim());
                s.setTurma(request.getParameter("turma").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
                s.setQtdAlunos(Integer.parseInt(request.getParameter("qtd-alunos")));
                s.getCurso().setId(Integer.parseInt(request.getParameter("curso").trim()));
                s.setDiaSemana(request.getParameter("dia-semana").trim());
                s.setModulo(request.getParameter("modulo").trim());
                s.setCurso(fac.getCursoDAO().selectId(s.getCurso()));

                Pessoa p = s.getPessoa();
                p.setEmail(ad.getMail(p));
                p.setNome(ad.getGivenName(p));
                p.setNomeCompleto(ad.getCN(p));

                for (String i : modulos) {
                    Modulo m = new Modulo();
                    m.setId(Integer.parseInt(i.trim()));
                    s.getModulos().add(m);
                }

                for (String i : softwares) {
                    Software sw = new Software();
                    sw.setId(Integer.parseInt(i.trim()));
                    sw = fac.getSoftwareDAO().selectId(sw);
                    s.getSoftwares().add(sw);
                }

                if (isEmpty) {
                    s.setObservacao("Nada informado.");
                } else {
                    s.setObservacao(request.getParameter("obs").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", ""));
                }

                s = fac.getSolicitacaoDAO().insertSolicitacoes(s);

                mail.setPessoa(p);
                mail.setSolicitacao(s);
                mail.sendMail(mail);
            }
        } catch (Exception e) {
            util.Logger.logSevere(e, this.getClass());
            Logger.logOutput("Houve um erro quando " + u.getNomeCompleto() + " (" + u.getUsername() + ") tentou "
                    + "inserir uma solicitação.");
            session.setAttribute("msg", "Erro ao efetivar a reserva.");
            session.setAttribute("status", "error");
            return request.getContextPath() + "/reserva/novo";
        }

        session.setAttribute("msg", "Solicitação efetuada com sucesso.");
        session.setAttribute("status", "success");
        Logger.logOutput(u.getNomeCompleto() + " (" + u.getUsername() + ") fez uma solitação.");
        return request.getContextPath() + "/reserva/novo";
    }
}
