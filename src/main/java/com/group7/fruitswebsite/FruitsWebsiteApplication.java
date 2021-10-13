package com.group7.fruitswebsite;

import com.group7.fruitswebsite.config.ApplicationConfig;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j
public class FruitsWebsiteApplication {

	public static void main(String[] args) {
		Start.context = SpringApplication.run(FruitsWebsiteApplication.class, args);
		log.info("Spring boot application run ok");
	}
}
