package com.group7.fruitswebsite.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
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

	public void setResponseResult(List<DhCategory> categories, ApiResponseResult result, Page<DhCategory> pageCate) {
		result.setData(categories);
		result.setPage(pageCate.getNumber() + 1);
		result.setPerPage(pageCate.getNumberOfElements());
		result.setTotal(categoryRepository.findAll().size());
	}

	public ResponseEntity<ApiResponse> saveOrUpdate(DhCategory category) {
		setNewCategory(category);
		ApiResponse apiResponse;
		try {
			categoryRepository.save(category);
			ApiResponse.ApiResponseResult result = new ApiResponse.ApiResponseResult();
			result.setData(new ArrayList<>(Collections.singletonList(category)));
			apiResponse = new ApiResponse.Builder().withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
					.withDateTime(DateUtil.currentDate())
					.withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage()).withResult(result).build();
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			apiResponse = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
					Constants.APIResponseStatus.FAILURE.getMessage(), null);
			return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(apiResponse);
		}
	}

	public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
		ApiResponseResult result = null;
		try {
			List<DhCategory> categories = new ArrayList<DhCategory>();
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
			category = categoryRepository.findById(id).get();
			ApiResponse.ApiResponseResult result = new ApiResponse.ApiResponseResult();
			result.setData(new ArrayList<>(Collections.singletonList(category)));
			apiResponse = new ApiResponse.Builder().withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
					.withDateTime(DateUtil.currentDate())
					.withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage()).withResult(result).build();
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
