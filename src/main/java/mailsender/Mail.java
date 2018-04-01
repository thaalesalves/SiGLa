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
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import model.Curso;
import model.Laboratorio;
import model.Licenca;
import model.Pessoa;
import model.Reserva;
import model.Software;
import model.Solicitacao;

@lombok.Getter
@lombok.Setter
public abstract class Mail {

    protected Reserva reserva = new Reserva();
    protected Solicitacao solicitacao = new Solicitacao();
    protected Pessoa funcionario = new Pessoa();
    protected Pessoa usuario = new Pessoa();
    protected Pessoa pessoa = new Pessoa();
    protected Software software = new Software();
    protected Laboratorio laboratorio = new Laboratorio();
    protected Curso curso = new Curso();
    protected Licenca licenca = new Licenca();
    protected String sender;
    protected String recipient;
    protected String subject;
    protected String message;

    public abstract String getMessage(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException;

    public abstract void sendMail(Mail mail) throws MessagingException, UnsupportedEncodingException, IOException, NullPointerException;

    public Session getSession() {
        final Properties props = new Properties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.migadu.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "sigla@thalesalv.es",
                        "Thales33"
                );
            }
        });

        return session;
    }

    public Session getSession(Mail m) {
        final Properties props = new Properties();
        final Mail mail = m;

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.migadu.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //return new PasswordAuthentication(mail.getPessoa().getEmail(), mail.getPessoa().getSenha());
                return new PasswordAuthentication(
                        mail.getFuncionario().getEmail(),
                        mail.getFuncionario().getSenha()
                );
            }
        });

        return session;
    }
}
