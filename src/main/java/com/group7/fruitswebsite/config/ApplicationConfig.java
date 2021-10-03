package com.group7.fruitswebsite.config;

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

	public static void loadConfig() {
        try {
            String logFilePath = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator + LOGGER_FILE;
            String configFilePath = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator + APP_CONF_FILE;
            PropertyConfigurator.configure(logFilePath);
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(configFilePath);
            try{
                properties.load(fileInputStream);
                RESOLVER_PREFIX = properties.getProperty("resolve_prefix_mvc");
                RESOLVER_SUFFIX = properties.getProperty("resolve_suffix_mvc");
            } finally {
                fileInputStream.close();
            }
            log.info("Load all configuration ok");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error load all configuration, ", ex);
        }
    }
}
