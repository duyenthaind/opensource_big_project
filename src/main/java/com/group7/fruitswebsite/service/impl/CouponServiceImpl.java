package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhCouponDto;
import com.group7.fruitswebsite.entity.DhCoupon;
import com.group7.fruitswebsite.model.CouponModel;
import com.group7.fruitswebsite.repository.CouponRepository;
import com.group7.fruitswebsite.service.CouponService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class CouponServiceImpl implements CouponService {

    private static final long DAY_TO_MILLIS = 24 * 60 * 60 * 1000L;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private CouponRepository couponRepository;

    @Override
    public ResponseEntity<ApiResponse> getOneCoupon(Integer id) {
        try {
            Optional<DhCoupon> optional = couponRepository.findById(id);
            ApiResponse.ApiResponseResult responseResult = parseResponseResult(optional);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error(String.format("Error get one coupon id = %s", id), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public ResponseEntity<ApiResponse> getOneCoupon(String code) {
        try {
            Optional<DhCoupon> optional = couponRepository.findByCode(code);
            ApiResponse.ApiResponseResult responseResult = parseResponseResult(optional);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error(String.format("Error get one coupon code = %s", code), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public ResponseEntity<ApiResponse> getOneCoupon(CouponModel couponModel) {
        return getOneCoupon(couponModel.getCode());
    }

    private ApiResponse.ApiResponseResult parseResponseResult(Optional<DhCoupon> optional) throws JsonProcessingException {
        if (optional.isPresent()) {
            DhCoupon dhCoupon = optional.get();
            if (dhCoupon.getStartTime() + dhCoupon.getDuration() * DAY_TO_MILLIS < System.currentTimeMillis()) {
                log.info(String.format("Coupon %s is expired", dhCoupon.getId()));
                return null;
            }
            DhCouponDto couponDto = objectMapper.readValue(objectMapper.writeValueAsString(dhCoupon), DhCouponDto.class);
            return ApiResponseUtil.mapResultWithOnlyData(Collections.singletonList(couponDto));
        }
        return null;
    }

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
}
