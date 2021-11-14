package com.group7.fruitswebsite.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

	private UserService userService;
	private UserDetails userDetails;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user-profile")
	public String userProfile(Model model,@RequestParam String username) {
		model.addAttribute("user",userService.getUserByUsernameAsDto(username));
		return "client/user-profile";
	}
}
