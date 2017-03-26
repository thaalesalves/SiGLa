/**
* Copyright (C) 2016 Thales Alves Pereira
* 
*  This file is part of SiGla.

*   SiGla is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.

*   SiGla is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.

*   You should have received a copy of the GNU General Public License
*   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
**/

package mailsender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import model.Reserva;
import model.Solicitacao;

public abstract class Mail {

    private Reserva reserva = new Reserva();
    private Solicitacao solicitacao = new Solicitacao();
    private String sender;
    private String recipient;
    private String subject;
    private String message;

    public abstract String getMessageProfessor(Mail mail);
    public abstract String getMessageFuncionario(Mail mail);
    public abstract void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException;

    // <editor-fold defaultstate="collapsed" desc="Método da API: getSession(Mail)">
    public Session getSession(Mail mail) {
        final Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 587);
        
        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thaalesalves@outlook.com", "Thales33");
            }
        });
        
        return session;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos de Acesso">
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }
    
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    } //</editor-fold>
}
