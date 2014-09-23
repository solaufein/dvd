package pl.radek.dvd.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * User: Sola
 * Date: 2014-09-23
 * Time: 14:42
 */
public class SmtpAuthenticator extends Authenticator {
    private String username;
    private String password;

    public SmtpAuthenticator(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
