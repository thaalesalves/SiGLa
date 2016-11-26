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

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: sendMail(Mail)">
    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(mail.getSession(mail));
            message.setFrom(new InternetAddress(mail.getReserva().getPessoa().getEmail()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
            message.setSubject(mail.getSubject());
            message.setText(mail.getMessage());
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            logger(e);
        } catch (Exception e) {
            logger(e);
        }
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: getMessage(Mail)">
    @Override
    public String getMessage(Mail mail) {
        return "";
    } //</editor-fold>
}
