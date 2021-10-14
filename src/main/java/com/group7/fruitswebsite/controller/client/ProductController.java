package com.group7.fruitswebsite.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.service.ProductImageService;
import com.group7.fruitswebsite.service.ProductService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author duyenthai
 */
@Log4j
@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private ProductImageService productImageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/product/save")
    public ResponseEntity<ApiResponse> saveOne(@RequestBody DhProductModel dhProductModel) {
        try {
            ResponseEntity<ApiResponse> res = null;
            if (dhProductModel.getCategoryId() == null) {
                return ApiResponseUtil.getStatusNotFoundCategory();
            }
            Optional<DhCategory> optional = categoryService.getById(dhProductModel.getCategoryId());
            if (!optional.isPresent()) {
                return ApiResponseUtil.getStatusNotFoundCategory();
            }
            String json = objectMapper.writeValueAsString(dhProductModel);
            DhProduct dhProduct = objectMapper.readValue(json, DhProduct.class);
            if (dhProduct != null) {
                res = productService.saveOne(dhProduct);
            }
            if (!StringUtil.isNullOrEmpty(dhProductModel.getImagePath())) {
                DhProductImage productImage = createNewProductImage(dhProductModel, dhProduct);
                productImageService.saveOne(productImage);
            }
            return res;
        } catch (Exception ex) {
            log.error("Error", ex);
        }
        return ApiResponseUtil.get403Entity();
    }

    private DhProductImage createNewProductImage(DhProductModel model, DhProduct product) {
        DhProductImage productImage = new DhProductImage();
        productImage.setName(model.getImageName());
        productImage.setPath(model.getImagePath());
        productImage.setDhProduct(product);
        long current = System.currentTimeMillis();
        productImage.setCreatedDate(current);
        productImage.setUpdatedDate(current);
        return productImage;
    }

    @Autowired
    public void setProductRepository(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductImageService(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }
}
