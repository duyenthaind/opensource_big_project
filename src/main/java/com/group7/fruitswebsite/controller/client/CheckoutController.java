package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.service.OrderService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.SecurityUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody DhOrderModel dhOrderModel) {
        return orderService.saveOne(dhOrderModel);
    }

    @DeleteMapping("/checkouts")
    public ResponseEntity<ApiResponse> deleteOne(@RequestBody Integer orderId) {
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
