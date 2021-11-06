package com.group7.fruitswebsite.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.group7.fruitswebsite.dto.search.ProductCondition;
import com.group7.fruitswebsite.entity.DhProductImage;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.service.ProductService;
import com.group7.fruitswebsite.service.impl.img.ImageProductServiceImpl;
import com.group7.fruitswebsite.util.ApiResponseUtil;

import lombok.extern.log4j.Log4j;
import org.springframework.web.multipart.MultipartFile;


@Controller(value = "productAdminController")
@Log4j
@RequestMapping(value = "/api/product/v1")
public class ProductController {

    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ApiResponse> addNewProduct(@ModelAttribute DhProductModel dhProductModel) {
        log.debug(dhProductModel.toString());
        ImageService imageService = new ImageProductServiceImpl();
        List<String> imagePath = imageService.saveUploadedMultiFiles(dhProductModel.getFiles());
        dhProductModel.setPathUploadedAvatar(imagePath);
        return productService.saveOne(dhProductModel);
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return productService.getAllWithPaging(page, size);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id) {
        return productService.getOne(id);
    }

    @PutMapping("/products")
    public ResponseEntity<ApiResponse> updateProduct(@ModelAttribute DhProductModel dhProductModel) {
        log.info(dhProductModel.toString());
        if (dhProductModel.getId() == null) {
            log.error(String.format("Drop all action with model %s because it has no id", dhProductModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_ID_IS_NOT_DEFINED, HttpStatus.FORBIDDEN);
        }
        MultipartFile[] files = dhProductModel.getFiles();
        if (files != null && files.length > 0) {
            ImageService imageService = new ImageProductServiceImpl();
            List<Integer> existedFiles = new ArrayList<>();
            for (int index = -1; ++index < files.length; ) {
                Optional<DhProductImage> optional = imageService.checkExists(files[index], dhProductModel.getId());
                if (optional.isPresent()) {
                    existedFiles.add(index);
                    dhProductModel.addProductImages(optional.get());
                }
            }
            existedFiles.forEach(index -> ArrayUtils.remove(files, index));
            List<String> imagePath = imageService.saveUploadedMultiFiles(files);
            dhProductModel.setPathUploadedAvatar(imagePath);
            dhProductModel.setFiles(files);
        }
        return productService.update(dhProductModel);
    }

    @DeleteMapping("/products")
    public ResponseEntity<ApiResponse> deleteProduct(@RequestParam Integer id) {
        return productService.delete(id);
    }

    @GetMapping("/products/searches")
    public ResponseEntity<ApiResponse> search(@RequestBody List<ProductCondition> conditions){
        return productService.search(conditions);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
