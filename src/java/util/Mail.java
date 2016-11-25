package util;

import java.io.UnsupportedEncodingException;
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
import model.Reserva;

public class Mail {

    // <editor-fold defaultstate="collapsed" desc="Atributos de modelo e métodos de acesso">
    private String subject;
    private String remetente;
    private String destinatario;
    private String mensagem;
    private Pessoa pessoa;
    private Reserva reserva;

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getSubject() {
        return subject;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static String getSERVIDOR_SMTP() {
        return SERVIDOR_SMTP;
    }

    public static int getPORTA_SERVIDOR_SMTP() {
        return PORTA_SERVIDOR_SMTP;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    } // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Métodos e atributos de envio de email">
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private static final String SERVIDOR_SMTP = "smtp.office365.com";
    private static final int PORTA_SERVIDOR_SMTP = 587;

    public void sendMail(Mail mail) throws UnsupportedEncodingException {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail.getPessoa().getEmail(), mail.getPessoa().getSenha());
            }
        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
            message.setFrom(new InternetAddress(mail.getPessoa().getEmail()));
            message.setSubject(mail.getSubject());
            message.setText(mail.getMensagem());
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }
    
    public void sendEmailReservaSemestral(Pessoa p, Reserva r) throws UnsupportedEncodingException {
        final String msg = "Olá, " + p.getNome() + ".\n\n" +
                "Sua reserva será processada, e daremos o retorno assim que for avaliada a possibilidade de alocação.\n\n" +
                "Nome completo: " + p.getNomeCompleto() + 
                "\nTurma: " + r.getTurma() +
                "\nCurso: " + r.getCurso().getModalidade() + " em " + r.getCurso().getNome() +
                "\nMódulo: " + r.getModulo() +
                "\nSoftware: " + r.getSoftware().getFabricante() + " " + r.getSoftware().getNome() +
                "\nDia da semana: " + r.getDiaDaSemana() + 
                "\nQuantidade de alunos: " + r.getQtd() +
                "\nObservação: " + r.getObservacao();
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getEmail(), p.getSenha());
            }
        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(p.getEmail()));
            message.setFrom(new InternetAddress(p.getEmail()));
            message.setSubject("Reserva de Laboratório");
            message.setText(msg);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    } // </editor-fold>
}
