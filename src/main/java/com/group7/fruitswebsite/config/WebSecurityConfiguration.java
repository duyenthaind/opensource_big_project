package com.group7.fruitswebsite.config;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.service.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author duyenthai
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    @Primary
    public UserDetailsService userDetailsServiceBean(){
        return new JwtUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**", "/js/**", "/img/**", "/upload/**", "/fonts/**","/vendor/**","/data-table/**", "/v1/authenticate").permitAll()
                    .antMatchers("/admin/**", "/api-admin/**").hasAnyAuthority(Constants.RoleName.ADMIN.getName(), Constants.RoleName.SUPER_ADMIN.getName())
                    .antMatchers("/api-super/**").hasAnyAuthority(Constants.RoleName.SUPER_ADMIN.getName())
                    .antMatchers("/user-profile/**", "/user-carts/**", "/checkout/**")
                        .hasAnyAuthority(Constants.RoleName.USER.getName(),Constants.RoleName.ADMIN.getName(), Constants.RoleName.SUPER_ADMIN.getName())
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/authenticate").defaultSuccessUrl("/home",true)
                .failureUrl("/login?login_error=true").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/home").invalidateHttpSession(true)
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedPage("/403")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void setJwtAuthenticationEntryPoint(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }
}
