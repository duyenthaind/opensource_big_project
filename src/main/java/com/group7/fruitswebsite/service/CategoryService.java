package com.group7.fruitswebsite.service;

import org.springframework.http.ResponseEntity;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhCategoryModel;
import com.group7.fruitswebsite.entity.DhCategory;

import java.util.Optional;

public interface CategoryService {
	ResponseEntity<ApiResponse> save(DhCategoryModel dhCategoryModel);
	
	ResponseEntity<ApiResponse> update(DhCategoryModel dhCategoryModel);

	Optional<DhCategory> getById(int id);
	
	ResponseEntity<ApiResponse> getOne(Integer id);
	
	ResponseEntity<ApiResponse> deleteById(Integer id);
	
	ResponseEntity<ApiResponse> getAllWithPaging(int page, int size);
}
