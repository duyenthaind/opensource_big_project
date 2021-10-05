package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.util.DateUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;

import java.util.Date;

@Log4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<ApiResponse> saveOne(DhProduct dhProduct) {
        try {
            long currentTimestamp = System.currentTimeMillis();
            dhProduct.setCreatedDate(currentTimestamp);
            dhProduct.setUpdatedDate(currentTimestamp);
            dhProduct.setSeo(String.format("%s-%d", dhProduct.getName(), currentTimestamp));
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
}
