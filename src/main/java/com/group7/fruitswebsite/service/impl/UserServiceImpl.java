package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Optional<DhUser> findByUserName(String userName) {
        try {
            return userRepository.findByUsername(userName);
        } catch (Exception ex) {
            log.error("Error when find user by username");
        }
        return Optional.empty();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
