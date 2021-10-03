package com.group7.fruitswebsite.service.impl;

import java.util.Date;

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

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	private DhCategory setNewCategory(DhCategory category) {
		category.setSeo(StringUtil.seo(category.getName()) + "-" + System.currentTimeMillis());
		category.setCreatedDate(new Date());
//		category.setCreatedBy(createdBy);
		return category;
	}
	
	private DhCategory setUpdateCategory(DhCategory category) {
		category.setSeo(StringUtil.seo(category.getName()) + "-" + System.currentTimeMillis());
		category.setUpdatedDate(new Date());
//		category.setUpdatedBy(updatedBy);
		return category;
	}
	
	
	public ResponseEntity<ApiResponse> saveOne(DhCategory category) {
		setNewCategory(category);
		ApiResponse apiResponse;
		try {
			categoryRepository.save(category);
			apiResponse = new ApiResponse(Constants.APIResponseStatus.SUCCESS_200.getStatus(), DateUtil.currentDate(),
					Constants.APIResponseStatus.SUCCESS_200.getMessage(), category);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
					Constants.APIResponseStatus.FAILURE.getMessage(), null);
			return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(apiResponse);
		}
	}

}
