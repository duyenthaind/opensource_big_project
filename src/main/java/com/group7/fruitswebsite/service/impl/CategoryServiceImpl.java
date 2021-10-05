package com.group7.fruitswebsite.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.StringUtil;

@Log4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private DhCategory setNewCategory(DhCategory category) {
        category.setSeo(StringUtil.seo(category.getName()) + "-" + System.currentTimeMillis());
        category.setCreatedDate(System.currentTimeMillis());
        return category;
    }

    private DhCategory setUpdateCategory(DhCategory category) {
        category.setSeo(StringUtil.seo(category.getName()) + "-" + System.currentTimeMillis());
        category.setUpdatedDate(System.currentTimeMillis());
        return category;
    }


    public ResponseEntity<ApiResponse> saveOne(DhCategory category) {
        setNewCategory(category);
        ApiResponse apiResponse;
        try {
            categoryRepository.save(category);
            ApiResponse.ApiResponseResult result = new ApiResponse.ApiResponseResult();
            result.setData(new ArrayList<>(Collections.singletonList(category)));
            apiResponse = new ApiResponse.Builder().withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withDateTime(DateUtil.currentDate())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withResult(result)
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
                    Constants.APIResponseStatus.FAILURE.getMessage(), null);
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(apiResponse);
        }
    }

    @Override
    public Optional<DhCategory> getById(int id) {
        try {
            DhCategory category = categoryRepository.getById(id);
            return Optional.of(category);
        } catch (Exception ex) {
            log.error("Error get category by id, ", ex);
        }
        return Optional.empty();
    }

}
