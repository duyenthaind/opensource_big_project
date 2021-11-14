package com.group7.fruitswebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author duyenthai
 */
@Data
@AllArgsConstructor
public class UserResponse {
    private String username;
    private List<String> roles;
}
