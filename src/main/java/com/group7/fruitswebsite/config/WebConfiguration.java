package com.group7.fruitswebsite.config;


import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@Log4j
public class WebConfiguration implements WebMvcConfigurer {
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/client/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/client/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/client/fonts/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/client/img/");
		
		registry.addResourceHandler("/admin/css/**").addResourceLocations("classpath:/static/admin/css/");
		registry.addResourceHandler("/admin/js/**").addResourceLocations("classpath:/static/admin/js/");
		registry.addResourceHandler("/admin/fonts/**").addResourceLocations("classpath:/static/admin/fonts/");
		registry.addResourceHandler("/admin/images/**").addResourceLocations("classpath:/static/admin/images/");
		registry.addResourceHandler("/admin/media/**").addResourceLocations("classpath:/static/admin/media/");
		registry.addResourceHandler("/admin/vendor/**").addResourceLocations("classpath:/static/admin/vendor/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
	}
}
