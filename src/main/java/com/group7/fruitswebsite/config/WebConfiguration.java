package com.group7.fruitswebsite.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootConfiguration
@EnableWebMvc
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
	}
	
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
	    InternalResourceViewResolver resolver= new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/view/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}
}
