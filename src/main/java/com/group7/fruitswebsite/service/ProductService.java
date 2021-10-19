package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.model.DhProductModel;

import org.springframework.http.ResponseEntity;

public interface ProductService {
	ResponseEntity<ApiResponse> saveOne(DhProductModel dhProductModel);
}
