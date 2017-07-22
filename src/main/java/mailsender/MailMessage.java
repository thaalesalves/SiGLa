/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
*
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

public class MailMessage extends Mail {

    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        final Message message = new MimeMessage(getSession(mail));
        message.setFrom(new InternetAddress(mail.getPessoa().getNomeCompleto() + "<" + mail.getPessoa().getEmail() + ">"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        message.setSentDate(new Date());
        Transport.send(message);
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: getMessageProfessor(Mail)">
    @Override
    public String getMessage(Mail mail) {
        return "";
    }//</editor-fold>
}
