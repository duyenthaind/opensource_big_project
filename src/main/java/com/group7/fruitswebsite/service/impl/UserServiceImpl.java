package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.DhUserDto;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.util.DtoUtil;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Optional<DhUser> findByUserName(String userName) {
        try {
            return userRepository.findByUsername(userName);
        } catch (Exception ex) {
            log.error("Error when find user by username");
        }
        return Optional.empty();
    }
    
    @Override
    public DhUserDto getUserByUsernameAsDto(String username) {
        try {
            DhUserDto result = new DhUserDto();
            Optional<DhUser> dhUser = findByUserName(username);
            if(dhUser.isPresent()) {
            	result = DtoUtil.getDtoFromUserDetail(dhUser.get(), objectMapper);
            }
            return result;         
        } catch (Exception ex) {
            log.error("Get user error, ", ex);
        }
        return null;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
