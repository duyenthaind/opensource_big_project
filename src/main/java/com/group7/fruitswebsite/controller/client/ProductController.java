package com.group7.fruitswebsite.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.service.ProductService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@Log4j
@RestController
@RequestMapping(value = "/v1")
public class ProductController {
    private ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/product/save")
    public ResponseEntity<ApiResponse> saveOne(@RequestBody DhProductModel dhProductModel) {
        try {
            String json = objectMapper.writeValueAsString(dhProductModel);
            DhProduct dhProduct = objectMapper.readValue(json, DhProduct.class);
            return productService.save(dhProduct);
        } catch (Exception ex) {
            log.error("Error", ex);
        }
        return ApiResponseUtil.get404Entity();
    }

    @Autowired
    public void setProductRepository(ProductService productService) {
        this.productService = productService;
    }
}
