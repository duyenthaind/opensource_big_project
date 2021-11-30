package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.CouponModel;
import com.group7.fruitswebsite.service.CouponService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@RestController(value = "couponController")
@RequestMapping(value = "/v1/api/client-coupon")
@Log4j
public class CouponController {

    private CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<ApiResponse> validateCoupon(@RequestBody CouponModel couponModel) {
        if (StringUtils.isEmpty(couponModel.getCode())) {
            log.error(String.format("Drop all action for model %s because it has no code identity", couponModel));
            return ApiResponseUtil.getBaseFailureStatus();
        }
        return couponService.getOneCoupon(couponModel);
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }
}
