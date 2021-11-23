package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhCartDto;
import com.group7.fruitswebsite.model.CartModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author duyenthai
 */
public interface CartService {
    List<DhCartDto> findAllCart(String username);

    ResponseEntity<ApiResponse> updateCart(CartModel cartModel);

    ResponseEntity<ApiResponse> deleteCart(CartModel cartModel);

    ResponseEntity<ApiResponse> addCart(CartModel cartModel);
}
