package com.group7.fruitswebsite.service;

import org.springframework.http.ResponseEntity;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;

public interface CategoryService {
	public ResponseEntity<ApiResponse> saveOne(DhCategory category);
}
