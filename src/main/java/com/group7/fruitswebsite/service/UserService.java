package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.entity.DhUser;

public interface UserService {
	DhUser loadUserByUsername(String userName);

	DhUser findByUserName(String userName);
}
