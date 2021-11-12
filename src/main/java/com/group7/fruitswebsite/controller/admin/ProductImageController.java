package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.service.ProductImageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@RestController(value = "productImageControllerAdmin")
@Log4j
@RequestMapping("/admin/api/product-image/v1")
public class ProductImageController {
    private ProductImageService productImageService;

    @DeleteMapping("/product-images")
    public ResponseEntity<ApiResponse> deleteProductImage(@RequestParam Integer id){
        return productImageService.delete(id);
    }

    @Autowired
    public void setProductImageService(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }
}
