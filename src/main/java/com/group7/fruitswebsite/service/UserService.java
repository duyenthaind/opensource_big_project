package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.entity.DhUser;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface UserService {
    Optional<DhUser> findByUserName(String userName);
}
