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
import util.Logger;

public class MailSolicitacao extends Mail {

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: sendMail(Mail)">
    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(getSession(mail));
            message.setFrom(new InternetAddress("thaalesalves@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getSolicitacao().getPessoa().getEmail()));
            message.setSubject("Solicitação de Reserva");
            message.setText(mail.getSolicitacao().getPessoa().getShownName() + " está solicitando uma reserva.\n"
                    + "Turma: " + mail.getSolicitacao().getTurma() + " de " + mail.getSolicitacao().getCurso().getModalidade() + " em " + mail.getSolicitacao().getCurso().getNome());
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            Logger.logWarning(e, this.getClass());
        } catch (Exception e) {
            Logger.logWarning(e, this.getClass());
        }
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: getMessageProfessor(Mail)">
    @Override
    public String getMessageProfessor(Mail mail) {
        return "";
    }//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: getMessageFuncionario(Mail)">
    @Override
    public String getMessageFuncionario(Mail mail) {
        return "";
    }//</editor-fold>
}
