package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.service.impl.ImageCategoryServiceImpl;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhCategoryModel;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.service.CategoryService;

@Controller
@RequestMapping(value = "/admin")
@Log4j
public class AdminController {

	private CategoryRepository categoryRepository;
	private CategoryService categoryService;

	@GetMapping(value = { "/index", "/", "/home", "" })
	public String index() {
		return "admin/index";
	}

	@GetMapping("/table")
	public String table(final Model model) {
		return "admin/table";
	}

	@GetMapping("/form")
	public String form() {
		return "admin/form";
	}

	@GetMapping("/getAllCate")
	public List<DhCategory> getAllCate() {
		return categoryRepository.findAll();
	}

	@PostMapping("/addcate")
	public ResponseEntity<ApiResponse> addNewCate(@ModelAttribute DhCategoryModel dhCategoryModel) throws IOException {
		ImageService imageService = new ImageCategoryServiceImpl();
		String avatarPath = imageService.saveUploadFiles(dhCategoryModel.getFile());
		if(StringUtil.isNullOrEmpty(avatarPath)){
			return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.AVATAR_DEFINED_BUT_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
		}
		log.info(String.format("uploaded image to system: %s", avatarPath));
		dhCategoryModel.setPathUploadedAvatar(avatarPath);
		return categoryService.save(dhCategoryModel);
	}

	@GetMapping("/api/v1/getAll")
	public ResponseEntity<ApiResponse> getAllCategoryWithPaging(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		return categoryService.getAllWithPaging(page, size);
	}
	
	@GetMapping("/api/v1/getOne")
	public ResponseEntity<ApiResponse> getOneCategory(@RequestParam Integer id){
		return categoryService.getOne(id);
	}

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
