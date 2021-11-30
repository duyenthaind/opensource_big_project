package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface OrderService {
    ResponseEntity<ApiResponse> saveOne(DhOrderModel dhOrderModel);
}
