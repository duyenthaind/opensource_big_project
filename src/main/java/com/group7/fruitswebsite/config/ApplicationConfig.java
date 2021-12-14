package com.group7.fruitswebsite.config;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author duyenthai
 */
@Log4j
public class ApplicationConfig {

    private ApplicationConfig() {
    }

    private static final Logger LOGGER = Logger.getLogger("ApplicationConfig");
    private static final String CONF_DIR = "config";
    private static final String APP_CONF_FILE = "configuration.properties";
    private static final String LOGGER_FILE = "log.properties";

    public static String RESOLVER_PREFIX;
    public static String RESOLVER_SUFFIX;
    public static String UPLOAD_DIR;
    public static String ROOT_UPLOAD_DIR;
    public static String CATEGORY_UPLOAD_RELATIVE_DIR;
    public static String PRODUCT_UPLOAD_RELATIVE_DIR;
    public static String BLOG_UPLOAD_RELATIVE_DIR;
    public static String USER_AVATAR_RELATIVE_DIR;
    public static Constants.EmailProvider EMAIL_PROVIDER;
    public static String INVOICE_MAIL_TEMPLATE;
    public static Constants.AuthenticationProtocol AUTHENTICATION_PROTOCOL;
    public static String EMAIL_HOST;
    public static String EMAIL_PORT;
    public static String EMAIL_USERNAME;
    public static String EMAIL_PASSWORD;
    public static String MOMO_PARTNER_CODE;
    public static String MOMO_ACCESS_KEY;
    public static String MOMO_SECRET_KEY;
    public static String MOMO_ENDPOINT;
    public static String MOMO_RETURN_URL;
    public static String MOMO_NOTIFY_URL;

    public static void loadConfig() {
        try {
            String logFilePath = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator + LOGGER_FILE;
            String configFilePath = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator + APP_CONF_FILE;
            PropertyConfigurator.configure(logFilePath);
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(configFilePath);
            try {
                properties.load(fileInputStream);
                RESOLVER_PREFIX = properties.getProperty("resolve_prefix_mvc");
                RESOLVER_SUFFIX = properties.getProperty("resolve_suffix_mvc");
                UPLOAD_DIR = properties.getProperty("relative_upload_dir");
                if (!StringUtil.isNullOrEmpty(UPLOAD_DIR)) {
                    ROOT_UPLOAD_DIR = System.getProperty("user.dir") + File.separator + UPLOAD_DIR;
                }
                CATEGORY_UPLOAD_RELATIVE_DIR = properties.getProperty("category_upload_dir");
                PRODUCT_UPLOAD_RELATIVE_DIR = properties.getProperty("product_upload_dir");
                BLOG_UPLOAD_RELATIVE_DIR = properties.getProperty("blog_upload_dir");
                USER_AVATAR_RELATIVE_DIR = properties.getProperty("user_avatar_upload_dir");
                EMAIL_PROVIDER = Constants.EmailProvider.getFromEnum(properties.getProperty("mail_service"));
                INVOICE_MAIL_TEMPLATE = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator
                        + "mail_template" + File.separator + properties.getProperty("invoice_mail_template");
                AUTHENTICATION_PROTOCOL = Constants.AuthenticationProtocol.getFromEnum(properties.getProperty("email_auth_protocol"));
                EMAIL_HOST = properties.getProperty("email_host");
                EMAIL_PORT = properties.getProperty("email_port");
                EMAIL_USERNAME = properties.getProperty("email_username");
                EMAIL_PASSWORD = properties.getProperty("email_password");
                MOMO_PARTNER_CODE = properties.getProperty("momo_partner_code");
                MOMO_ACCESS_KEY = properties.getProperty("lR6BvNhh7v87g4Ff");
                MOMO_SECRET_KEY = properties.getProperty("S9zu2fmRcpXvsQam1dRylrxYvhqZZGg7");
                MOMO_ENDPOINT = properties.getProperty("https://test-payment.momo.vn/gw_payment/transactionProcessor");
                MOMO_RETURN_URL = properties.getProperty("momo_return_url");
                MOMO_NOTIFY_URL = properties.getProperty("momo_notify_url");
            } finally {
                fileInputStream.close();
            }
            log.info("Load all configuration ok");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error load all configuration, ", ex);
        }
    }
}
