package com.group7.fruitswebsite.config;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
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
	private static final String LOGGER_FILE = "log.properties";

	public static void loadConfig() {
        try {
            String logFilePath = System.getProperty("user.dir") + File.separator + CONF_DIR + File.separator + LOGGER_FILE;
            PropertyConfigurator.configure(logFilePath);
            log.info("Load all configuration ok");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error load all configuration, ", ex);
        }
    }
}
