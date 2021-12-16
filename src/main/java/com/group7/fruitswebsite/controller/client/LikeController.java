package com.group7.fruitswebsite.controller.client;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.service.UserProductService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.SecurityUtil;
import com.group7.fruitswebsite.util.StringUtil;

@RestController
@RequestMapping("/liked/v1/api")
public class LikeController {

	private UserProductService userProductService;

	@GetMapping("/likeds")
	public ResponseEntity<ApiResponse> getAllForUser() {
		UserDetails details = SecurityUtil.getUserDetails();
		if (Objects.nonNull(details)) {
			return userProductService.getAllForUser(details.getUsername());
		}
		return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
				HttpStatus.FORBIDDEN);
	}
	
	@DeleteMapping("/likeds")
	public ResponseEntity<ApiResponse> deleteById(@RequestParam Integer productId){
		UserDetails details = SecurityUtil.getUserDetails();
		if (Objects.nonNull(details)) {
			return userProductService.deleteByProductIdAndUserid(productId, details.getUsername());
		}
		return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
				HttpStatus.FORBIDDEN);
	}

	@PostMapping("/liked")
	public ResponseEntity<ApiResponse> addLiked(@RequestParam Integer productId) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			if (!StringUtil.isNullOrEmpty(username)) {
				return userProductService.saveOne(productId, username);
			}
		}
		return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
				HttpStatus.FORBIDDEN);
	}

	@Autowired
	public void setUserProductService(UserProductService userProductService) {
		this.userProductService = userProductService;
	}

}
