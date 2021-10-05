package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.service.ProductImageService;
import com.group7.fruitswebsite.util.DateUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author duyenthai
 */
@Log4j
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private ProductImageRepository productImageRepository;

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhProductImage dhProductImage) {
        try {
            productImageRepository.save(dhProductImage);
            ApiResponse res = new ApiResponse.Builder()
                    .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withDateTime(DateUtil.currentDate())
                    .withResult(null)
                    .build();
            log.info(String.format("Save 1 new image product, id=%d", dhProductImage.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            ApiResponse res = new ApiResponse.Builder()
                    .withStatus(Constants.APIResponseStatus.FAILURE.getStatus())
                    .withMessage(ex.getMessage())
                    .withDateTime(DateUtil.currentDate())
                    .withResult(null)
                    .build();
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(res);
        }
    }

    @Override
    public Optional<DhProductImage> getById(int id) {
        try {
            DhProductImage productImage = productImageRepository.getById(id);
            return Optional.of(productImage);
        } catch (Exception ex) {
            log.error("Error get product image by id, ", ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DhProductImage> getByPath(String path) {
        try {
            DhProductImage productImage = productImageRepository.getByPath(path);
            return Optional.of(productImage);
        } catch (Exception ex) {
            log.error("Error get product by path, ", ex);
        }
        return Optional.empty();
    }
}
