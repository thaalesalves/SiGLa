package mailsender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSemestral extends Mail {

    // <editor-fold defaultstate="collapsed" desc="Métodos da Classe: sendMail(Mail)">
    @Override
    public void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException {
        try {
            final Message message = new MimeMessage(mail.getSession(mail));
            message.setFrom(new InternetAddress(mail.getReserva().getPessoa().getEmail()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReserva().getPessoa().getEmail()));
            message.setSubject("Reserva de Laboratório");
            message.setText(mail.getMessage(mail));
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
        return "Olá, " + mail.getReserva().getPessoa().getNome() + ".\n\n"
                + "Sua reserva será processada, e daremos o retorno assim que for avaliada a possibilidade de alocação.\n\n"
                + "Nome completo: " + mail.getReserva().getPessoa().getNomeCompleto()
                + "\nTurma: " + mail.getReserva().getTurma()
                + "\nCurso: " + mail.getReserva().getCurso().getModalidade() + " em " + mail.getReserva().getCurso().getNome()
                + "\nMódulo: " + mail.getReserva().getModulo()
                + "\nSoftware: " + mail.getReserva().getSoftware().getFabricante() + " " + mail.getReserva().getSoftware().getNome()
                + "\nDia da semana: " + mail.getReserva().getDiaDaSemana()
                + "\nQuantidade de alunos: " + mail.getReserva().getQtd()
                + "\nObservação: " + mail.getReserva().getObservacao();
    } //</editor-fold>
}
