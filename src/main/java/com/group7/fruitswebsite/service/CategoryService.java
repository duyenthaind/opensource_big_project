package com.group7.fruitswebsite.service;

import org.springframework.http.ResponseEntity;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;

import java.util.Optional;

public interface CategoryService {
	ResponseEntity<ApiResponse> saveOne(DhCategory category);

	Optional<DhCategory> getById(int id);
}
