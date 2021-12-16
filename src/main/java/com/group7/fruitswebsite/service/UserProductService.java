package com.group7.fruitswebsite.service;

import org.springframework.http.ResponseEntity;

import com.group7.fruitswebsite.dto.ApiResponse;

public interface UserProductService {
	ResponseEntity<ApiResponse> saveOne(Integer productId,String username);
	ResponseEntity<ApiResponse> getAllForUser(String username);
	ResponseEntity<ApiResponse> deleteByProductIdAndUserid(Integer productId,String username);
}
