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
import model.Modulo;
import model.Software;
import util.Logger;

public class SolicitacaoReprovacaoMail extends Mail {

    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress("SiGLa <sigla@thalesalv.es>"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getPessoa().getEmail()));
            message.setSubject("Solicitação de Reserva");
            message.setContent(getMessage(mail), "text/html");
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
        String modulos = "";
        
        for (Software s : mail.getReserva().getSoftwares()) {
            softwares = softwares + ", " + s.getFabricante() + " " + s.getNome();
        }
        
        for (Modulo m : mail.getReserva().getModulos()) {
            modulos = modulos + ", " + m.getId() + "º módulo";
        }
        
        return "Olá, " + mail.getPessoa().getNome() + "!\n"
                + "Sua solicitação foi aprovada!\n\n"
                + "Número da solicitação: " + mail.getReserva().getId() + "\n"
                + "Nome completo: " + mail.getPessoa().getNomeCompleto() + "\n"
                + "Turma: " + mail.getReserva().getTurma() + " de " + mail.getReserva().getCurso().getModalidade() + " em " + mail.getReserva().getCurso().getNome() + "\n"
                + "Dia da semana: " + mail.getReserva().getDiaDaSemana() + "\n"
                + "Quantidade de alunos: " + mail.getReserva().getQtdAlunos() + "\n"
                + "Módulos: " + modulos.substring(1) + "\n"
                + "Softwares: " + softwares.substring(1) + "\n"
                + "Laboratório: " + mail.getLaboratorio().getNumero();
    }
}
