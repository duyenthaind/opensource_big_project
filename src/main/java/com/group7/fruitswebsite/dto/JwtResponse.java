package com.group7.fruitswebsite.dto;

/**
 * @author duyenthai
 */
public class JwtResponse {
    private final String jwtAccessToken;

    public JwtResponse(String jwtAccessToken){
        this.jwtAccessToken = jwtAccessToken;
    }

    public String getJwtAccessToken() {
        return jwtAccessToken;
    }
}
