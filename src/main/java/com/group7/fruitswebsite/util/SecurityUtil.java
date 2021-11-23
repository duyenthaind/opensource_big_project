package com.group7.fruitswebsite.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author duyenthai
 */
public class SecurityUtil {
    private SecurityUtil() {

    }

    public static User getUserDetails() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
