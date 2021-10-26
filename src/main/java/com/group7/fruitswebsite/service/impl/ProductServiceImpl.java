package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import com.group7.fruitswebsite.util.StringUtil;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductImageRepository productImageRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DhProduct setNewProduct(DhProductModel dhProductModel) throws JsonProcessingException {
        String modelJson = objectMapper.writeValueAsString(dhProductModel);
        DhProduct dhProduct = objectMapper.readValue(modelJson, DhProduct.class);

        Optional<DhCategory> optionalDhCategory = categoryRepository.findById(dhProductModel.getCategoryId());
        optionalDhCategory.ifPresent(dhProduct::setCategory);

        dhProduct.setCreatedDate(System.currentTimeMillis());
        dhProduct.setUpdatedDate(0L);
        dhProduct.setSeo(StringUtil.seo(dhProductModel.getProductName()) + "-" + System.currentTimeMillis());

        return dhProduct;
    }


    public ResponseEntity<ApiResponse> saveOne(DhProductModel dhProductModel) {
        try {
            DhProduct dhProduct = setNewProduct(dhProductModel);
            productRepository.save(dhProduct);
            if (dhProductModel.getPathUploadedAvatar() != null && !dhProductModel.getPathUploadedAvatar().isEmpty()) {
                List<String> imagePath = dhProductModel.getPathUploadedAvatar();
                for (int i = 0; i < dhProductModel.getFiles().length; i++) {
                    DhProductImage dhProductImage = new DhProductImage();
                    dhProductImage.setCreatedBy(Constants.SystemUser.SYSTEM_USER_ID);
                    dhProductImage.setCreatedDate(System.currentTimeMillis());
                    dhProductImage.setName(dhProductModel.getFiles()[i].getOriginalFilename());
                    dhProductImage.setPath(imagePath.get(i).replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY));
                    dhProductImage.setDhProduct(dhProduct);
                    productImageRepository.save(dhProductImage);
                }
            }
            ApiResponse response = new ApiResponse.Builder()
                    .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withDateTime(DateUtil.currentDate())
                    .withResult(null)
                    .build();
            log.info(String.format("Save 1 new product, id=%d", dhProduct.getId()));
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error insert new product, ", ex);
            ApiResponse response = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
                    Constants.APIResponseStatus.FAILURE.getMessage(), null);
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(response);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAll() {
        try {
            List<DhProductDto> listAllProducts = getAllProductsAsDto();
            ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
            apiResponseResult.setData(listAllProducts);
            ApiResponse response = new ApiResponse.Builder()
                    .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withDateTime(DateUtil.currentDate())
                    .withResult(apiResponseResult)
                    .build();
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            log.error("Error get all product, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public List<DhProductDto> getAllProductsAsDto() {
        try {
            List<DhProductDto> result = new ArrayList<>();
            List<DhProduct> listAllProducts = productRepository.findAll();
            for (DhProduct dhProduct : listAllProducts) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get all product as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<DhProduct> pageProducts = productRepository.findAll(paging);
            List<DhProductDto> productDtos = pageProducts.getContent().stream()
                    .map(val -> DtoUtil.getDtoFromProduct(val, objectMapper, productImageRepository)).collect(Collectors.toList());
            ApiResponse.ApiResponseResult responseResult = new ApiResponse.ApiResponseResult();
            int totalProducts = productRepository.findAll().size();
            int totalPages = totalProducts % size == 0 ? totalProducts / size : totalProducts / size + 1;
            responseResult.setData(productDtos);
            responseResult.setPage(pageProducts.getNumber() + 1);
            responseResult.setPerPage(pageProducts.getNumberOfElements());
            responseResult.setTotalPages(totalPages);
            responseResult.setTotal(productDtos.size());
            return ResponseEntity.ok(new ApiResponse.Builder()
                    .withDateTime(DateUtil.currentDate())
                    .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withResult(responseResult).build());
        } catch (Exception ex) {
            log.error("Get all products with paging error ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getOne(int id) {
        try {
            Optional<DhProduct> optional = productRepository.findById(id);
            if (optional.isPresent()) {
                DhProduct product = optional.get();
                DhProductDto dto = DtoUtil.getDtoFromProduct(product, objectMapper, productImageRepository);
                ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
                apiResponseResult.setData(Collections.singletonList(dto));
                return ResponseEntity.ok(new ApiResponse.Builder()
                        .withDateTime(DateUtil.currentDate())
                        .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                        .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                        .withResult(apiResponseResult).build());
            }
            return ResponseEntity.ok(new ApiResponse());
        } catch (Exception ex) {
            log.error(String.format("Get one product with id %s error", id), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

}
