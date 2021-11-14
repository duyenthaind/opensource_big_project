package com.group7.fruitswebsite.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginSiginController {

	@GetMapping("/login")
	public String login() {
		return "client/login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "client/signup";
	}
}
