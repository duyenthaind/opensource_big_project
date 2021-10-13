package com.group7.fruitswebsite.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.CategoryDTO;
import com.group7.fruitswebsite.dto.ApiResponse.ApiResponseResult;
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

    private DhCategory setNewCategory(CategoryDTO categoryDTO) {
        DhCategory category = new DhCategory();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setSeo(StringUtil.seo(categoryDTO.getName()) + "-" + System.currentTimeMillis());
//		category.setAvatar(categoryDTO.getAvatarName());
        category.setCreatedDate(System.currentTimeMillis());
        if (!StringUtil.isNullOrEmpty(categoryDTO.getPathUploadedAvatar())) {
            String avatar = categoryDTO.getPathUploadedAvatar();
            category.setAvatar(avatar.replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY));
        }
        log.info("category : " + category);
        return category;
    }

    private DhCategory setUpdateCategory(CategoryDTO categoryDTO) {
        DhCategory category = new DhCategory();
        category.setSeo(StringUtil.seo(categoryDTO.getName()) + "-" + System.currentTimeMillis());
        category.setUpdatedDate(System.currentTimeMillis());
        return category;
    }

    public ResponseEntity<ApiResponse> save(CategoryDTO categoryDTO) {
        DhCategory category = setNewCategory(categoryDTO);
        ApiResponse apiResponse;
        try {
            categoryRepository.save(category);
            log.info("category after saved: " + category.getId());
            ApiResponse.ApiResponseResult result = new ApiResponse.ApiResponseResult();
            result.setData(new ArrayList<>(Collections.singletonList(category)));
            apiResponse = new ApiResponse.Builder().withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withDateTime(DateUtil.currentDate())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage()).withResult(result).build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            log.error("Save category error ", e);
            apiResponse = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
                    Constants.APIResponseStatus.FAILURE.getMessage(), null);
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(apiResponse);
        }
    }

    public void setResponseResult(List<DhCategory> categories, ApiResponseResult result, Page<DhCategory> pageCate) {
        result.setData(categories);
        result.setPage(pageCate.getNumber() + 1);
        result.setPerPage(pageCate.getNumberOfElements());
        result.setTotal(categoryRepository.findAll().size());
    }

    public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
        ApiResponseResult result = null;
        try {
            List<DhCategory> categories;
            Pageable paging = PageRequest.of(page, size);

            Page<DhCategory> pageCate;

            pageCate = categoryRepository.findAll(paging);

            categories = pageCate.getContent();

            result = new ApiResponseResult();
            if (categoryRepository.findAll().size() % size == 0) {
                result.setTotalPages(categoryRepository.findAll().size() / size);
            } else {
                result.setTotalPages((categoryRepository.findAll().size() / size) + 1);
            }
            setResponseResult(categories, result, pageCate);
            return ResponseEntity.ok(new ApiResponse(Constants.APIResponseStatus.SUCCESS_200.getStatus(),
                    DateUtil.currentDate(), Constants.APIResponseStatus.SUCCESS_200.getMessage(), result));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(),
                    DateUtil.currentDate(), Constants.APIResponseStatus.FAILURE.getMessage(), result));
        }
    }

    public ResponseEntity<ApiResponse> getOne(Integer id) {
        DhCategory category = null;
        ApiResponse apiResponse;
        try {
            Optional<DhCategory> optional = categoryRepository.findById(id);
            if (optional.isPresent()) {
                category = optional.get();
            } else {
                return ApiResponseUtil.getStatusNotFoundCategory();
            }
            ApiResponse.ApiResponseResult result = new ApiResponse.ApiResponseResult();
            result.setData(new ArrayList<>(Collections.singletonList(category)));
            apiResponse = new ApiResponse.Builder().withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withDateTime(DateUtil.currentDate())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withResult(result).build();
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
