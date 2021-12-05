package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.service.OrderService;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@RestController(value = "clientCheckoutController")
@RequestMapping(value = "/checkout/v1/api")
@Log4j
public class CheckoutController {
    private OrderService orderService;

    @GetMapping("/orders")
	public ResponseEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return orderService.getAllWithPaging(page, size);
	}
    
    @PostMapping(value = "/checkouts")
    public ResponseEntity<ApiResponse> placeOrder(@ModelAttribute DhOrderModel dhOrderModel) {
        return orderService.saveOne(dhOrderModel);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
