package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhOrderModelUpdate;
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
@RestController(value = "checkoutControllerAdmin")
@RequestMapping("/api-admin/checkout/v1")
@Log4j
public class CheckoutController {
	private OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return orderService.getAllWithPaging(page, size);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id) {
		return orderService.getOne(id);
	}

	@PutMapping("/orders")
	public ResponseEntity<ApiResponse> updateOrder(@RequestBody DhOrderModelUpdate orderModelUpdate) {
		if (Objects.isNull(orderModelUpdate.getOrderId())) {
			log.info(String.format("Drop all action for model %s because it has no identity ", orderModelUpdate));
		}
		return orderService.customUpdate(orderModelUpdate);
	}

	public ResponseEntity<ApiResponse> getAllForUser(int page, int size) {
		User currentUser = SecurityUtil.getUserDetails();
		if (currentUser != null) {
			return orderService.getAllForUser(currentUser.getUsername(), page, size);
		} else {
			return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
					HttpStatus.FORBIDDEN);
		}
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
