package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhOrderDto;
import com.group7.fruitswebsite.entity.*;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.model.DhOrderModelUpdate;
import com.group7.fruitswebsite.repository.*;
import com.group7.fruitswebsite.service.OrderService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import com.group7.fruitswebsite.util.SecurityUtil;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class OrderServiceImpl implements OrderService {

    private static final long DAY_TO_MILLIS = 24 * 60 * 60 * 1000L;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CouponRepository couponRepository;

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhOrderModel dhOrderModel) {
        try {
            DhOrder dhOrder = objectMapper.readValue(objectMapper.writeValueAsString(dhOrderModel), DhOrder.class);
            User currentUser = SecurityUtil.getUserDetails();
            String username = currentUser.getUsername();
            Optional<DhUser> optionalUser = userRepository.findByUsername(username);
            if (!optionalUser.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
            }
            List<DhCart> listCartsOfCurrentUser = cartRepository.findByUserId(optionalUser.get().getId());
            if (listCartsOfCurrentUser.isEmpty()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NO_CART, HttpStatus.GONE);
            }
            Optional<DhCoupon> optionalCoupon = couponRepository.findByCode(dhOrderModel.getCouponCode());
            optionalCoupon.ifPresent(dhOrder::setDhCoupon);
            dhOrder.setCreatedDate(System.currentTimeMillis());
            dhOrder.setDhUser(optionalUser.get());
            dhOrder.setCodeName(StringUtil.randomString(8, 1).toUpperCase());
            dhOrder.setIsPrepaid(false);
            readCartInformationAndSaveOrder(dhOrder, listCartsOfCurrentUser, dhOrder.getDhCoupon());
            orderRepository.save(dhOrder);
            log.info(String.format("Save order %s of user %s ", dhOrder.getId(), username));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Save order %s error", dhOrderModel), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllForUser(String username, int page, int size) {
        try {
            Optional<DhUser> currentUser = userRepository.findByUsername(username);
            if (!currentUser.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
            }
            Pageable pageable = PageRequest.of(page, size);
            int userId = currentUser.get().getId();
            Page<DhOrder> orders = orderRepository.findByUserId(userId, pageable);
            List<DhOrder> allOrdersOfUser = orderRepository.findByUserId(userId);
            List<DhOrderDto> result = orders.getContent().stream().map(val -> DtoUtil.getOrderDtoFromDhOrder(val, objectMapper, null)).collect(Collectors.toList());
            ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultFromList(result, orders, allOrdersOfUser.size(), size);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error("Error get all order, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DhOrder> pageOrders = orderRepository.findAll(pageable);
            List<DhOrderDto> orderDtos = pageOrders.getContent().stream()
                    .map(val -> DtoUtil.getOrderDtoFromDhOrder(val, objectMapper, null)).collect(Collectors.toList());
            int totalOrders = orderRepository.findAll().size();
            ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultFromList(orderDtos, pageOrders, totalOrders, size);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error("Error get all order, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> customUpdate(DhOrderModelUpdate orderModelUpdate) {
        try {
            DhOrder order = new DhOrder();
            order.setId(orderModelUpdate.getOrderId());
            order.setIsPrepaid(orderModelUpdate.getIsPrepaid());
            order.setOrderStatus(orderModelUpdate.getOrderStatus());
            orderRepository.customUpdate(order);
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Custom update model %s error ", orderModelUpdate), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getOne(int orderId) {
        try {
            Optional<DhOrder> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                DhOrderDto dto = DtoUtil.getOrderDtoFromDhOrder(order.get(), objectMapper, orderProductRepository);
                ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultWithOnlyData(Collections.singletonList(dto));
                return ApiResponseUtil.getBaseSuccessStatus(responseResult);
            }
        } catch (Exception ex) {
            log.error(String.format("Get order %s error ", orderId), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public ResponseEntity<ApiResponse> getOneForUser(int orderId, String username) {
        try {
            Optional<DhOrder> order = orderRepository.findByIdAndUserName(orderId, username);
            if (order.isPresent()) {
                DhOrderDto dto = DtoUtil.getOrderDtoFromDhOrder(order.get(), objectMapper, orderProductRepository);
                ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultWithOnlyData(Collections.singletonList(dto));
                return ApiResponseUtil.getBaseSuccessStatus(responseResult);
            }
        } catch (Exception ex) {
            log.error(String.format("Get order %s, user %s error", orderId, username), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public ResponseEntity<ApiResponse> deleteOne(int orderId, String username) {
        try {
            Optional<DhUser> currentUser = userRepository.findByUsername(username);
            if (!currentUser.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
            }
            int userId = currentUser.get().getId();
            orderProductRepository.deleteByOrderId(orderId);
            orderRepository.deleteByIdAndUserId(orderId, userId);
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Error delete order %s of user %s", orderId, username),ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    private void readCartInformationAndSaveOrder(DhOrder dhOrder, List<DhCart> listCartsOfCurrentUser, DhCoupon coupon) {
        long totalAmount = 0L;
        if (!listCartsOfCurrentUser.isEmpty()) {
            for (DhCart index : listCartsOfCurrentUser) {
                DhOrderProduct orderProduct = new DhOrderProduct();
                orderProduct.setProductId(index.getProductId());
                orderProduct.setQuantity(index.getQuantity());
                orderProduct.setOrder(dhOrder);
                orderProduct.setName(index.getName());
                orderProduct.setPrice(index.getPrice());
                totalAmount += index.getPrice() * index.getQuantity();
                dhOrder.addOrderProduct(orderProduct);
                orderProductRepository.save(orderProduct);
                cartRepository.delete(index);
            }
        }
        if (Objects.nonNull(coupon) && System.currentTimeMillis() < coupon.getStartTime() + coupon.getDuration() * DAY_TO_MILLIS) {
            totalAmount = coupon.getTotal() > totalAmount ? totalAmount - coupon.getTotal() : 0;
        }
        dhOrder.setTotal(totalAmount);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderProductRepository(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
}
