package com.group7.fruitswebsite.controller.admin;

import lombok.extern.log4j.Log4j;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group7.fruitswebsite.entity.DhRole;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.CategoryService;

@Controller
@RequestMapping(value = "/admin")
@Log4j
public class AdminController {

	private CategoryRepository categoryRepository;
	private CategoryService categoryService;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping(value = { "/index", "/", "/home", "" })
	public String index() {
		Set<DhRole> dhRoles = userRepo.findById(1).get().getDhRoles();
		for (DhRole dhRole : dhRoles) {
			System.out.println(dhRole.getName());
		}		
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

}
