package com.group7.fruitswebsite.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.service.UserService;

@RestController(value = "userControllerAdmin")
@RequestMapping("/api-admin/v1/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<ApiResponse> test(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		return userService.getAllWithPage(page, size);
	}
}
