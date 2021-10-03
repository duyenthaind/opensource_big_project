package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.common.Constants;
import lombok.extern.log4j.Log4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.StringUtil;

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
	public ResponseEntity<ApiResponse> addNewCate(@RequestBody DhCategory category) {
		log.info(category.toString());
		return categoryService.saveOne(category);
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
