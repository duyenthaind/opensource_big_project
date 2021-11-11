package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Optional<DhUser> findByUserName(String userName) {
		try {
			return userRepository.findByUsername(userName);
		} catch (Exception ex) {
			log.info("Error load user name", ex);
			return Optional.empty();
		}
	}
	
}
