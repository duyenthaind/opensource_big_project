package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.model.DhOrderModelUpdate;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface OrderService {
    ResponseEntity<ApiResponse> saveOne(DhOrderModel dhOrderModel);

    ResponseEntity<ApiResponse> getAllForUser(String username, int page, int size);

    ResponseEntity<ApiResponse> getAllWithPaging(int page, int size);

    ResponseEntity<ApiResponse> customUpdate(DhOrderModelUpdate orderModelUpdate);

    ResponseEntity<ApiResponse> getOne(int orderId);

    ResponseEntity<ApiResponse> getOneForUser(int orderId, String username);

    ResponseEntity<ApiResponse> deleteOne(int orderId, String username);

    ResponseEntity<ApiResponse> deleteOne(int orderId);

    Long calculateTotalAmountOfCurrentUser(String couponCode);
}
