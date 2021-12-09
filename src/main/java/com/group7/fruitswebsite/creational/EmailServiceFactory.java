package com.group7.fruitswebsite.creational;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.service.EmailService;
import com.group7.fruitswebsite.service.impl.SmtpEmailServiceImpl;

/**
 * @author duyenthai
 */
public class EmailServiceFactory {
    private EmailServiceFactory() {
    }

    public static EmailService getEmailService() {
        switch (ApplicationConfig.EMAIL_PROVIDER) {
            case SMTP:
                return new SmtpEmailServiceImpl();
            default:
                return null;
        }
    }
}
