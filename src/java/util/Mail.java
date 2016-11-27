package util;

import model.Reserva;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Pessoa;

public class Mail {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public void sendMail(Mail mail) {
        try {
            final Message message = new MimeMessage(getSession(mail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReserva().getPessoa().getEmail()));
            message.setFrom(new InternetAddress(mail.getReserva().getPessoa().getEmail()));
            message.setSubject(mail.getAssunto());
            message.setText(mail.getMensagem());
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="getSession(Mail mail)">
    public Session getSession(Mail mail) {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail.getReserva().getPessoa().getEmail(), mail.getReserva().getPessoa().getSenha());
            }

        });

        return session;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getEmailProperties()">
    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", "smtp.office365.com");
        config.put("mail.smtp.port", 587);
        return config;
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos de acesso">
    private Pessoa pessoa = new Pessoa();
    private Reserva reserva = new Reserva();
    private String remetente;
    private String destinatario;
    private String assunto;
    private String mensagem;

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getMensagem() {
        return mensagem;
    } // </editor-fold>
}
