package com.group7.fruitswebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<DhUser> optional  = userService.findByUserName(username);
		return optional.orElse(null);
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
