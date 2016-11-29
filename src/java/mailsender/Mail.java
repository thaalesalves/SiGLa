package mailsender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import model.Reserva;

public abstract class Mail {

    private Reserva reserva = new Reserva();
    private String sender;
    private String recipient;
    private String subject;
    private String message;

    public abstract void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException;

    public abstract String getMessage(Mail mail);

    // <editor-fold defaultstate="collapsed" desc="Método da API: getSession(Mail)">
    public Session getSession(Mail mail) {
        final Properties props = new Properties();
        
        props.put("mail.smtp.host", "direct.uesp.net.br");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.user", "thales");
        props.put("mail.smtp.password", "rosebud");
        
        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thales", "rosebud");
            }
        });
        
        return session;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos de Acesso">
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
