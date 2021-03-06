package com.group7.fruitswebsite.config;

import com.group7.fruitswebsite.service.impl.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author duyenthai
 */
@Component
@Log4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = StringUtils.EMPTY;
        String accessToken = StringUtils.EMPTY;

        if (!StringUtils.isEmpty(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            accessToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUserNameFromAccessToken(accessToken);
            } catch (IllegalArgumentException ex) {
                log.error("Unable to get JWT token");
                log.debug("Unable to get JWT token", ex);
            } catch (ExpiredJwtException ex) {
                log.error("JWT Token has expired");
                log.debug("JWT Token has expired", ex);
            }
        } else {
            log.debug("JWT Token does not begin with Bearer");
        }

        // get accessToken from cookies instead
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(accessToken) && request.getCookies() != null) {
            try {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("accessToken")) {
                        accessToken = cookie.getValue();
                    }
                }
                if (!StringUtils.isEmpty(accessToken)) {
                    username = jwtTokenUtil.getUserNameFromAccessToken(accessToken);
                }
            } catch (Exception ex) {
                log.error("Parse cookies to get JWT error");
                log.debug("Parse cookies to get JWT error", ex);
            }
        }

        if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtTokenUtil.validateAccessToken(accessToken, userDetails))) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            log.debug("Guest mode, this user cannot do what a logged in user can do!");
        }
        filterChain.doFilter(request, response);
    }

    @Autowired
    public void setJwtUserDetailsService(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
}