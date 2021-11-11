package com.group7.fruitswebsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		DhUser dhUser = userService.loadUserByUsername(username);
		return dhUser;
	}

}
