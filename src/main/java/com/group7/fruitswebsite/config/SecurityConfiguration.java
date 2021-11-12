package com.group7.fruitswebsite.config;

import com.group7.fruitswebsite.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/css/**", "/js/**", "/img/**", "/upload/**", "/fonts/**","/vendor/**").permitAll()
		.antMatchers("/admin/**").hasAnyAuthority("ADMIN").antMatchers("/user-profile").hasAnyAuthority("ADMIN","CLIENT")
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/perform_login").defaultSuccessUrl("/home",true)
		.failureUrl("/login?login_error=true").permitAll()
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/home").invalidateHttpSession(true)
		.deleteCookies("SESSION").permitAll().and().exceptionHandling().accessDeniedPage("/403");
	} 
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(new BCryptPasswordEncoder(4));
	}

	@Override
	@Bean
	@Primary
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailsServiceImpl();
	}
	
}
