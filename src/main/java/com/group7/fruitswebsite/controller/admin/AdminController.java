package com.group7.fruitswebsite.controller.admin;

import lombok.extern.log4j.Log4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.service.UserService;

@Controller
@RequestMapping(value = "/admin")
@Log4j
public class AdminController {

	private CategoryRepository categoryRepository;
	private CategoryService categoryService;
	private UserService userService;

	@GetMapping(value = { "/index", "/", "/home", "" })
	public String index() {		
		return "admin/index";
	}


	@GetMapping("/table")
	public String table(final Model model) {
		model.addAttribute("category",categoryRepository.findAll());
		return "admin/table";
	}

	@GetMapping("/form")
	public String form() {
		return "admin/form";
	}
	
	@GetMapping("/blog")
	public String blog() {
		return "admin/blog";
	}

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
