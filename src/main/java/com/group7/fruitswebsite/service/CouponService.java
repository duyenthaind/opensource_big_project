package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.CouponModel;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface CouponService {
    ResponseEntity<ApiResponse> getOneCoupon(Integer id);

    ResponseEntity<ApiResponse> getOneCoupon(String code);

    ResponseEntity<ApiResponse> getOneCoupon(CouponModel couponModel);
}
