package com.group7.fruitswebsite;

import com.group7.fruitswebsite.config.ApplicationConfig;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author duyenthai
 */
@Log4j
public class Start {
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		ApplicationConfig.loadConfig();
		context = SpringApplication.run(FruitsWebsiteApplication.class, args);
		log.info("Application start ok");
	}
}
