package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.CheckoutResponse;
import com.group7.fruitswebsite.dto.DhOrderDto;
import com.group7.fruitswebsite.entity.*;
import com.group7.fruitswebsite.job.EmailPoolJob;
import com.group7.fruitswebsite.model.DhOrderModel;
import com.group7.fruitswebsite.model.DhOrderModelUpdate;
import com.group7.fruitswebsite.repository.*;
import com.group7.fruitswebsite.service.OrderService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import com.group7.fruitswebsite.util.SecurityUtil;
import com.group7.fruitswebsite.util.StringUtil;
import com.group7.fruitswebsite.worker.EmailPoolWorker;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
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
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhOrderModel dhOrderModel) {
        try {
            DhOrder dhOrder = objectMapper.readValue(objectMapper.writeValueAsString(dhOrderModel), DhOrder.class);
            User currentUser = SecurityUtil.getUserDetails();
            if (Objects.isNull(currentUser)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
            }
            String username = currentUser.getUsername();
            Optional<DhUser> optionalUser = userRepository.findByUsername(username);
            if (!optionalUser.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND, HttpStatus.FORBIDDEN);
            }
            Constants.PaymentMethod paymentMethod = Constants.PaymentMethod.getFromEnum(dhOrder.getPaymentMethod());
            if (Objects.isNull(paymentMethod)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PAYMENT_METHOD_IS_NOT_SUPPORTED, HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isEmpty(dhOrderModel.getPayUrl()) && paymentMethod.equals(Constants.PaymentMethod.MOMO)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.MOMO_IS_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);
            }
            CheckoutResponse checkoutResponse = findCheckoutResponse(paymentMethod, dhOrderModel.getPayUrl());
            List<DhCart> listCartsOfCurrentUser = cartRepository.findByUserId(optionalUser.get().getId());
            if (listCartsOfCurrentUser.isEmpty()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NO_CART, HttpStatus.GONE);
            }
            long totalAmount = listCartsOfCurrentUser.stream().reduce(0L, (res, cart) -> res + cart.getPrice() * cart.getQuantity(), Long::sum);
            Optional<DhCoupon> optionalCoupon = couponRepository.findByCode(dhOrderModel.getCouponCode());
            DhCoupon currentCoupon = optionalCoupon.orElse(null);
            long couponPrice = 0L;
            if (Objects.nonNull(currentCoupon) && currentCoupon.getStartTime() + currentCoupon.getDuration() * DAY_TO_MILLIS < System.currentTimeMillis()) {
                dhOrder.setDhCoupon(currentCoupon);
                couponPrice = currentCoupon.getTotal();
            }
            optionalCoupon.ifPresent(dhOrder::setDhCoupon);
            dhOrder.setCreatedDate(System.currentTimeMillis());
            dhOrder.setDhUser(optionalUser.get());
            dhOrder.setCodeName(StringUtil.randomString(8, 1).toUpperCase());
            dhOrder.setIsPrepaid(false);
            dhOrder.setTotal(couponPrice > totalAmount ? 0 : totalAmount - couponPrice);
            dhOrder.setOrderStatus(Constants.OrderStatus.UNAPPROVED.getStatus());
            readCartInformationAndSaveOrder(dhOrder, listCartsOfCurrentUser, dhOrder.getDhCoupon());
            orderRepository.save(dhOrder);
            log.info(String.format("Save order %s of user %s ", dhOrder.getId(), username));
            // momo transaction
            if (paymentMethod.equals(Constants.PaymentMethod.MOMO)
                    && !StringUtils.isEmpty(dhOrderModel.getTransactionId()) && !StringUtils.isEmpty(dhOrderModel.getRequestId())) {
                saveMomoTransaction(dhOrderModel, dhOrder);
            }
            // send mail to customer un synchronous
            EmailPoolJob emailJob = new EmailPoolJob(Constants.JobType.EMAIL_ORDER, username, optionalUser.get().getEmail());
            emailJob.getCustoms().put("orderId", dhOrder.getId());
            EmailPoolWorker.pubJob(emailJob);
            ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultWithOnlyData(Collections.singletonList(checkoutResponse));
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
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
            log.error("Error get all order for user, ", ex);
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
    public ResponseEntity<ApiResponse> getByOrderStatusWithPaging(int page, int size, int orderStatus) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DhOrder> pageOrders = orderRepository.findByOrderStatus(orderStatus, pageable);
            List<DhOrderDto> orderDtos = pageOrders.getContent().stream()
                    .map(val -> DtoUtil.getOrderDtoFromDhOrder(val, objectMapper, null)).collect(Collectors.toList());
            int totalOrders = orderRepository.findByOrderStatus(orderStatus).size();
            ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultFromList(orderDtos, pageOrders, totalOrders, size);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error("Error get all order status, ", ex);
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
            log.error(String.format("Error delete order %s of user %s", orderId, username), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public ResponseEntity<ApiResponse> deleteOne(int orderId) {
        try {
            orderProductRepository.deleteByOrderId(orderId);
            orderRepository.deleteById(orderId);
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Error delete order %s ", orderId), ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public Long calculateTotalAmountOfCurrentUser(String couponCode) {
        try {
            User currentUser = SecurityUtil.getUserDetails();
            if (Objects.isNull(currentUser)) {
                return null;
            }
            String username = currentUser.getUsername();
            Optional<DhUser> optionalUser = userRepository.findByUsername(username);
            if (!optionalUser.isPresent()) {
                return null;
            }
            List<DhCart> listCartsOfCurrentUser = cartRepository.findByUserId(optionalUser.get().getId());
            if (listCartsOfCurrentUser.isEmpty()) {
                return null;
            }
            long totalAmount = listCartsOfCurrentUser.stream().reduce(0L, (res, cart) -> res + cart.getPrice() * cart.getQuantity(), Long::sum);
            Optional<DhCoupon> optionalCoupon = couponRepository.findByCode(couponCode);
            DhCoupon currentCoupon = optionalCoupon.orElse(null);
            long couponPrice = 0L;
            if (Objects.nonNull(currentCoupon) && currentCoupon.getStartTime() + currentCoupon.getDuration() * DAY_TO_MILLIS < System.currentTimeMillis()) {
                couponPrice = currentCoupon.getTotal();
            }
            return couponPrice > totalAmount ? 0 : totalAmount - couponPrice;
        } catch (Exception ex) {
            log.error("Calculate total amount error", ex);
        }
        return null;
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

    private void saveMomoTransaction(DhOrderModel dhOrderModel, DhOrder dhOrder) {
        log.info(String.format("Process traction momo, order %s", dhOrder.getId()));
        DhTransaction dhTransaction = new DhTransaction();
        dhTransaction.setTransactionId(dhOrderModel.getTransactionId());
        dhTransaction.setRequestId(dhOrderModel.getRequestId());
        dhTransaction.setOrderId(dhOrder.getId());
        dhTransaction.setPrice(dhOrder.getTotal());
        dhTransaction.setIsPaid(false);
        transactionRepository.save(dhTransaction);
    }

    private CheckoutResponse findCheckoutResponse(Constants.PaymentMethod paymentMethod, String payUrl) {
        switch (paymentMethod) {
            case MOMO:
                return CheckoutResponse.getSuccessMomoResponseWithPayUrl(payUrl);
            default:
                return CheckoutResponse.getSuccessCodResponse();
        }
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

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
