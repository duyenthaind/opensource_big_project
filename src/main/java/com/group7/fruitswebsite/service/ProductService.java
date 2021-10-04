package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProduct;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ApiResponse> save(DhProduct dhProduct);
}
