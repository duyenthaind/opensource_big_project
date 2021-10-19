package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.StringUtil;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;

import java.io.File;
import java.util.List;
import java.util.Optional;

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
        dhProduct.setSeo(StringUtil.seo(dhProductModel.getProductName()) + "-" + System.currentTimeMillis());
        if (dhProductModel.getPathUploadedAvatar() != null && !dhProductModel.getPathUploadedAvatar().isEmpty()) {
            List<String> imagePath = dhProductModel.getPathUploadedAvatar();
            for (int i = 0; i < dhProductModel.getFiles().length; i++) {
                DhProductImage dhProductImage = new DhProductImage();
                dhProductImage.setCreatedBy(Constants.SystemUser.SYSTEM_USER_ID);
                dhProductImage.setCreatedDate(System.currentTimeMillis());
                dhProductImage.setName(dhProductModel.getFiles()[i].getName());
                dhProductImage.setPath(imagePath.get(i).replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY));
                dhProductImage.setDhProduct(dhProduct);
                productImageRepository.save(dhProductImage);
            }
        }
        return dhProduct;
    }


    public ResponseEntity<ApiResponse> saveOne(DhProductModel dhProductModel) {
        try {
            DhProduct dhProduct = setNewProduct(dhProductModel);
            productRepository.save(dhProduct);
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

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
