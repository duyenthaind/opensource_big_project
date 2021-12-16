package com.group7.fruitswebsite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhUserProductDto;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.entity.DhUserProduct;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.repository.UserProductRepository;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.UserProductService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserProductServiceImpl implements UserProductService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	private UserRepository userRepository;

	private UserProductRepository userProductRepository;

	private ProductRepository productRepository;

	private ProductImageRepository productImageRepository;

	@Override
	public ResponseEntity<ApiResponse> getAllForUser(String username) {
		List<DhUserProductDto> dhUserProductDtos = new ArrayList<DhUserProductDto>();
		try {
			Optional<DhUser> currentUser = userRepository.findByUsername(username);
			if (!currentUser.isPresent()) {
				return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
						HttpStatus.FORBIDDEN);
			}
			int userId = currentUser.get().getId();
			dhUserProductDtos = userProductRepository.findByUserId(userId).stream()
					.map(val -> DtoUtil.getDtoFromUserProduct(val, objectMapper)).collect(Collectors.toList());
			 return ApiResponseUtil.getBaseSuccessStatus(ApiResponseUtil.mapResultWithOnlyData(dhUserProductDtos));
		} catch (Exception ex) {
			log.error("Error get all liked product, ", ex);
			return ApiResponseUtil.getBaseFailureStatus();
		}
	}
	
	@Override
	public ResponseEntity<ApiResponse> deleteByProductIdAndUserid(Integer productId,String username) {
		DhUserProductDto dhUserProductDtos = new DhUserProductDto();
		try {
			Optional<DhUser> currentUser = userRepository.findByUsername(username);
			if (!currentUser.isPresent()) {
				return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
						HttpStatus.FORBIDDEN);
			}	
			int userId = currentUser.get().getId();
			userProductRepository.deleteByUserIdAndProductId(userId, productId);
			return ApiResponseUtil.getCustomStatusWithMessage("delete success", HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			log.error("Error delete liked product, ", ex);
			return ApiResponseUtil.getBaseFailureStatus();
		}
	}

	@Override
	public ResponseEntity<ApiResponse> saveOne(Integer productId, String username) {
		DhUserProduct userProducts = new DhUserProduct();
		try {
			Optional<DhUser> currentUser = userRepository.findByUsername(username);
			if (!currentUser.isPresent()) {
				return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
						HttpStatus.FORBIDDEN);
			}
			int userId = currentUser.get().getId();
			Optional<DhUserProduct> optionalUserProduct = userProductRepository.findByProductId(productId);
			if(!optionalUserProduct.isPresent()) {
				Optional<DhProduct> optionalProduct = productRepository.findById(productId);
				if (optionalProduct.isPresent()) {
					userProducts.setUserId(userId);
					userProducts.setProductId(productId);
					userProducts.setCreatedDate(System.currentTimeMillis());
					List<DhProductImage> listProductImages = productImageRepository
							.getByDhProductId(optionalProduct.get().getId());
					if (!listProductImages.isEmpty()) {
						userProducts.setAvatar(listProductImages.get(0).getPath());
					}
					userProducts.setName(optionalProduct.get().getName());
					userProducts.setPrice(optionalProduct.get().getPriceSale());
					userProductRepository.save(userProducts);
				}
			}else {
				return ApiResponseUtil.getCustomStatusWithMessage("already have", HttpStatus.ALREADY_REPORTED);
			}	
			
			return ApiResponseUtil.getBaseSuccessStatus(null);
		} catch (Exception ex) {
			log.error("Error save liked product, ", ex);
			return ApiResponseUtil.getBaseFailureStatus();
		}
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setUserProductRepository(UserProductRepository userProductRepository) {
		this.userProductRepository = userProductRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setProductImageRepository(ProductImageRepository productImageRepository) {
		this.productImageRepository = productImageRepository;
	}

}
