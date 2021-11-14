package com.group7.fruitswebsite.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author duyenthai
 */
@Data
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
