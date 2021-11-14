package com.group7.fruitswebsite.model;

import lombok.Data;

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
