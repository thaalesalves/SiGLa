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
package mailsender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Software;
import util.Logger;

public class SolicitacaoMail extends Mail {

    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress("Laboratório de Informática <thaalesalves@gmail.com>"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getPessoa().getEmail()));
            message.setSubject("Solicitação de Reserva");
            message.setText(getMessage(mail));
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            Logger.logWarning(e, this.getClass());
        } catch (Exception e) {
            Logger.logWarning(e, this.getClass());
        }
    }

    @Override
    public String getMessage(Mail mail) {
        String softwares = "";
        
        for (Software s : mail.getSolicitacao().getSoftwares()) {
            softwares = softwares + ", " + s.getFabricante() + " " + s.getNome();
        }
        
        return "Olá, " + mail.getPessoa().getNome() + "!\n"
                + "Sua solicitação foi recebida e será avaliada pela nossa equipe. Você receberá um email dizendo se foi possível atendê-lo ou não.\n\n"
                + "Número da solicitação: " + mail.getSolicitacao().getId() + "\n"
                + "Nome completo: " + mail.getPessoa().getNomeCompleto() + "\n"
                + "Turma: " + mail.getSolicitacao().getTurma() + " de " + mail.getSolicitacao().getCurso().getModalidade() + " em " + mail.getSolicitacao().getCurso().getNome() + "\n"
                + "Dia da semana: " + mail.getSolicitacao().getDiaSemana() + "\n"
                + "Quantidade de alunos: " + mail.getSolicitacao().getQtdAlunos() + "\n"
                + "Módulo: " + mail.getSolicitacao().getModulo() + "\n"
                + "Software: " + softwares.substring(1);
    }
}
