package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.service.OrderService;

import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.MomoHelper;
import com.group7.fruitswebsite.util.SecurityUtil;

import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.allinone.processor.allinone.CaptureMoMo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author duyenthai
 */
@RestController(value = "clientCheckoutController")
@RequestMapping(value = "/checkout/v1/api")
@Log4j
public class CheckoutController {
    private OrderService orderService;

    @GetMapping("/checkouts")
    public ResponseEntity<ApiResponse> getAllOrder(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        User currentUser = SecurityUtil.getUserDetails();
        if (Objects.isNull(currentUser)) {
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        }
        return orderService.getAllForUser(currentUser.getUsername(), page, size);
    }

    @GetMapping("/checkouts/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id) {
        User currentUser = SecurityUtil.getUserDetails();
        if (Objects.isNull(currentUser)) {
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        }
        return orderService.getOneForUser(id, currentUser.getUsername());
    }

    @PostMapping("/checkouts")
    public ResponseEntity<ApiResponse> placeOrder(@ModelAttribute DhOrderModel dhOrderModel, HttpServletResponse response) {
        Constants.PaymentMethod paymentMethod = Constants.PaymentMethod.getFromEnum(dhOrderModel.getPaymentMethod());
        if (Objects.nonNull(paymentMethod) && paymentMethod.equals(Constants.PaymentMethod.MOMO)) {
            try {
                String transactionId = UUID.randomUUID().toString();
                String requestId = UUID.randomUUID().toString();
                String orderInfo = (new Date()).toString();
                Long totalOrderAmount = orderService.calculateTotalAmountOfCurrentUser(dhOrderModel.getCouponCode());
                dhOrderModel.setTransactionId(transactionId);
                dhOrderModel.setRequestId(requestId);
                CaptureMoMoResponse captureMoMoResponse = CaptureMoMo.process(MomoHelper.getMomoEnvironment(), transactionId, requestId,
                        totalOrderAmount.toString(), orderInfo, ApplicationConfig.MOMO_RETURN_URL, ApplicationConfig.MOMO_NOTIFY_URL, StringUtils.EMPTY);
                if (Objects.nonNull(captureMoMoResponse)) {
                    response.sendRedirect(captureMoMoResponse.getPayUrl());
                }
            } catch (Exception ex) {
                log.error("Process transaction Momo error", ex);
            }
        }
        return orderService.saveOne(dhOrderModel);
    }

    @DeleteMapping("/checkouts/{orderId}")
    public ResponseEntity<ApiResponse> deleteOne(@PathVariable Integer orderId) {
        User currentUser = SecurityUtil.getUserDetails();
        if (Objects.isNull(currentUser)) {
            log.info(String.format("Drop all action for orderID %s because no user is specified", orderId));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        }
        return orderService.deleteOne(orderId, currentUser.getUsername());
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
