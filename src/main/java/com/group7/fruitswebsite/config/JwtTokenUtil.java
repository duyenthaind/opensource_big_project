package com.group7.fruitswebsite.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * @author duyenthai
 */
@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60L;

    @Value("${jwt.secret}")
    private String secret;

    public String getUserNameFromAccessToken(String accessToken) {
        return getClaimForToken(accessToken, Claims::getSubject);
    }

    public Date getExpirationDateFromAccessToken(String accessToken) {
        return getClaimForToken(accessToken, Claims::getExpiration);
    }

    public List<String> getAllRolesOfUser(String accessToken){
        Claims claims = getAllClaimsFromAccessToken(accessToken);
        return claims.get("roles", List.class);
    }

    public <T> T getClaimForToken(String accessToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromAccessToken(accessToken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromAccessToken(String accessToken) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken).getBody();
    }

    private Boolean isTokenExpired(String accessToken) {
        final Date expiration = getExpirationDateFromAccessToken(accessToken);
        return expiration.before(new Date());
    }

    public String generateAccessToken(UserDetails userDetails) {
        return doGenerateAccessToken(new ArrayList<>(userDetails.getAuthorities()), userDetails.getUsername());
    }

    private String doGenerateAccessToken(List<GrantedAuthority> claims, String subject) {
        return Jwts.builder().claim("roles", claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateAccessToken(String accessToken, UserDetails userDetails) {
        final String username = getUserNameFromAccessToken(accessToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(accessToken);
    }
}
