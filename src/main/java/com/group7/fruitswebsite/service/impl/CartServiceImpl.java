package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhCartDto;
import com.group7.fruitswebsite.entity.DhCart;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.CartModel;
import com.group7.fruitswebsite.repository.CartRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.CartService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class CartServiceImpl implements CartService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductImageRepository productImageRepository;
    private ProductRepository productRepository;

    @Override
    public List<DhCartDto> findAllCart(String username) {
        try {
            Optional<DhUser> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                List<DhCart> currentCarts = cartRepository.findByUserId(optionalUser.get().getId());
                return currentCarts.stream().map(val -> DtoUtil.getCartDtoFromDhCart(val, objectMapper)).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            log.error(String.format("Find all cart for user %s error, ", username), ex);
        }
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<ApiResponse> updateCart(CartModel cartModel) {
        try {
            Optional<DhCart> currentCartItem = cartRepository.findByUserIdAndProductId(cartModel.getUserId(), cartModel.getProductId());
            if (currentCartItem.isPresent()) {
                DhCart updatedCart = currentCartItem.get();
                updatedCart.setQuantity(cartModel.getQuantity());
                updatedCart.setUpdatedBy(cartModel.getUserId());
                updatedCart.setUpdatedDate(System.currentTimeMillis());
                cartRepository.save(updatedCart);
                log.info(String.format("Update cart item %s of user %s", updatedCart.getId(), updatedCart.getUserId()));
            }
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(String.format("Update cart for user %s error", cartModel.getUserId()), ex);
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCart(CartModel cartModel) {
        try {
            Optional<DhCart> currentCartItem = cartRepository.findByUserIdAndProductId(cartModel.getUserId(), cartModel.getProductId());
            currentCartItem.ifPresent(dhCart -> cartRepository.delete(dhCart));
            log.info(String.format("Delete cart item of user %s", cartModel.getUserId()));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(String.format("Update cart for user %s error", cartModel.getUserId()), ex);
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CART_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> addCart(CartModel cartModel) {
        try {
            Optional<DhCart> existedCart = cartRepository.findByUserIdAndProductId(cartModel.getUserId(), cartModel.getProductId());
            if (existedCart.isPresent()) {
                DhCart currentCart = existedCart.get();
                currentCart.setQuantity(currentCart.getQuantity() + 1);
                cartRepository.save(currentCart);
                log.info(String.format("Update cart item for user %s", cartModel.getUserId()));
                return ApiResponseUtil.getBaseSuccessStatus(null);
            }
            DhCart dhCart = objectMapper.readValue(objectMapper.writeValueAsString(cartModel), DhCart.class);
            dhCart.setCreatedDate(System.currentTimeMillis());
            dhCart.setCreatedBy(cartModel.getUserId());
            dhCart.setQuantity(1);
            Optional<DhProduct> product = productRepository.findById(cartModel.getProductId());
            if (product.isPresent()) {
                dhCart.setName(product.get().getName());
                List<DhProductImage> listProductImages = productImageRepository.getByDhProductId(product.get().getId());
                if (!listProductImages.isEmpty()) {
                    dhCart.setAvatar(listProductImages.get(0).getPath());
                }
            }
            cartRepository.save(dhCart);
            log.info(String.format("Save new cart item for user %s", cartModel.getUserId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Save new cart item error", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
