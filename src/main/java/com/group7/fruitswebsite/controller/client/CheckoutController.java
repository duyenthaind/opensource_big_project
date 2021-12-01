package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.service.OrderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@RestController(value = "clientCheckoutController")
@RequestMapping(value = "/checkout/v1/api")
@Log4j
public class CheckoutController {
    private OrderService orderService;

    @PostMapping(value = "/checkouts")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody DhOrderModel dhOrderModel) {
        return orderService.saveOne(dhOrderModel);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
