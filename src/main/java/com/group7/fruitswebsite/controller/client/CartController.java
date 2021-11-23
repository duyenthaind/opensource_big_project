package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhCartDto;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.CartModel;
import com.group7.fruitswebsite.service.CartService;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.SecurityUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author duyenthai
 */
@RestController
@RequestMapping("value = /v1/api/cart")
@Log4j
public class CartController {

    private CartService cartService;
    private UserService userService;

    @GetMapping(value = "/carts")
    public ResponseEntity<ApiResponse> refreshCart() {
        User currentUser = SecurityUtil.getUserDetails();
        if (currentUser != null) {
            List<DhCartDto> listCartDtos = cartService.findAllCart(currentUser.getUsername());
            return ApiResponseUtil.getBaseSuccessStatus(ApiResponseUtil.mapResultWithOnlyData(listCartDtos));
        }
        return ApiResponseUtil.getBaseSuccessStatus(null);
    }

    @PostMapping(value = "/carts")
    public ResponseEntity<ApiResponse> addCart(@RequestBody CartModel cartModel, HttpServletRequest httpServletRequest) {
        if (cartModel.getProductId() == null) {
            log.info(String.format("Drop all action for model %s because it has no productId", cartModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_ID_MUST_BE_NON_NULL, HttpStatus.EXPECTATION_FAILED);
        }
        User currentUser = SecurityUtil.getUserDetails();
        if (currentUser != null) {
            Optional<DhUser> user = userService.findByUserName(currentUser.getUsername());
            if (user.isPresent()) {
                cartModel.setUserId(user.get().getId());
                cartModel.setQuantity(1);
                return cartService.addCart(cartModel);
            } else {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
            }
        }
        Set<CartModel> carts = checkCartInSessionAndCreateNewIfNeeded(httpServletRequest);
        carts.add(cartModel);
        httpServletRequest.getSession().setAttribute(Constants.SessionItem.CART, carts);
        return ApiResponseUtil.getBaseSuccessStatus(null);
    }

    @PutMapping(value = "/carts")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody CartModel cartModel, HttpServletRequest httpServletRequest) {
        if (cartModel.getProductId() == null) {
            log.info(String.format("Drop all action for model %s because it has no productId", cartModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_ID_MUST_BE_NON_NULL, HttpStatus.EXPECTATION_FAILED);
        }
        if (cartModel.getQuantity() == null || cartModel.getQuantity() <= 0) {
            log.info(String.format("Drop all action for model %s because it has no quantity", cartModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_QUANTITY_MUST_BE_NON_NULL, HttpStatus.EXPECTATION_FAILED);
        }
        User currentUser = SecurityUtil.getUserDetails();
        if (currentUser != null) {
            Optional<DhUser> user = userService.findByUserName(currentUser.getUsername());
            if (user.isPresent()) {
                cartModel.setUserId(user.get().getId());
                return cartService.updateCart(cartModel);
            } else {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
            }
        }
        Set<CartModel> carts = checkCartInSessionAndCreateNewIfNeeded(httpServletRequest);
        for (CartModel index : carts) {
            if (Objects.equals(cartModel.getQuantity(), index.getQuantity())) {
                index.setQuantity(index.getQuantity() + 1);
                break;
            }
        }
        return ApiResponseUtil.getBaseSuccessStatus(null);
    }

    @DeleteMapping(value = "/carts")
    public ResponseEntity<ApiResponse> deleteCart(@RequestParam Integer productId, HttpServletRequest httpServletRequest) {
        User currentUser = SecurityUtil.getUserDetails();
        if (currentUser != null) {
            Optional<DhUser> user = userService.findByUserName(currentUser.getUsername());
            if (user.isPresent()) {
                CartModel cartModel = new CartModel();
                cartModel.setProductId(productId);
                cartModel.setUserId(user.get().getId());
                return cartService.deleteCart(cartModel);
            } else {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
            }
        }
        Set<CartModel> carts = checkCartInSessionAndCreateNewIfNeeded(httpServletRequest);
        for (CartModel index : carts) {
            if (Objects.equals(productId, index.getProductId())) {
                carts.remove(index);
                break;
            }
        }
        return ApiResponseUtil.getBaseSuccessStatus(null);
    }

    private Set<CartModel> checkCartInSessionAndCreateNewIfNeeded(HttpServletRequest httpServletRequest) {
        Set<CartModel> carts = (Set<CartModel>) httpServletRequest.getSession().getAttribute(Constants.SessionItem.CART);
        if (carts == null) {
            carts = new HashSet<>();
            httpServletRequest.getSession().setAttribute(Constants.SessionItem.CART, carts);
        }
        return carts;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
