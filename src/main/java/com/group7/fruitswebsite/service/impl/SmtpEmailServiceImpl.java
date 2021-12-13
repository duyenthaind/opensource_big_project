package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.service.EmailService;
import lombok.extern.log4j.Log4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author duyenthai
 */
@Log4j
public class SmtpEmailServiceImpl implements EmailService {
    @Override
    public void send(String toEmail, String body) {
        try {
            MimeMessage mimeMessage = new MimeMessage(loadSession());
            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mimeMessage.addHeader("format", "flowed");
            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

            mimeMessage.setFrom(new InternetAddress(ApplicationConfig.EMAIL_USERNAME, "FruitShop7"));
            mimeMessage.setReplyTo(InternetAddress.parse(ApplicationConfig.EMAIL_USERNAME, false));
            mimeMessage.setSubject("Invoice order from FruitWebsite");
            mimeMessage.setContent(body, "text/html");
            mimeMessage.setSentDate(new Date());
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(mimeMessage);
            log.info(String.format("Send mail to %s at %s", toEmail, new Date()));
        } catch (Exception ex) {
            log.error(String.format("Send mail to %s error, ", toEmail), ex);
        }
    }

    private Session loadSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", ApplicationConfig.EMAIL_HOST);
        properties.put("mail.smtp.port", ApplicationConfig.EMAIL_PORT);
        properties.put("mail.smtp.auth", true);
        switch (ApplicationConfig.AUTHENTICATION_PROTOCOL) {
            case TLS:
                properties.put("mail.smtp.starttls.enable", true);
                break;
            case SSL:
                properties.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.ssl.checkserveridentity", true);
                properties.put("mail.smtp.socketFactory.port", ApplicationConfig.EMAIL_PORT);
                break;
            default:
                break;
        }

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ApplicationConfig.EMAIL_USERNAME, ApplicationConfig.EMAIL_PASSWORD);
            }
        };
        return Session.getDefaultInstance(properties, authenticator);
    }
}
