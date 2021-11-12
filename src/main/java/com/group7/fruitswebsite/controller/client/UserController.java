package com.group7.fruitswebsite.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user-profile")
	public String userProfile() {
		return "client/user-profile";
	}
}
