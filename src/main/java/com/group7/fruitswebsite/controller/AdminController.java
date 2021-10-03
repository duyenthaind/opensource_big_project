package com.group7.fruitswebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.Category;
import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.utilities.DateUtil;
import com.group7.fruitswebsite.utilities.StringUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = {"/index","/","/home",""}, method = RequestMethod.GET)
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
	
	@PostMapping("/addcate")
	public ResponseEntity<ApiResponse> addNewCate(@RequestBody Category category){
		System.out.println(category.toString());
		ApiResponse apiResponse;
		try {
			category.setSeo(StringUtil.seo(category.getName()));
			System.out.println("here");
			categoryRepository.save(category);
			apiResponse = new ApiResponse(200,DateUtil.currentDate(),"success",category);
			return ResponseEntity.status(200).body(apiResponse);
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse = new ApiResponse(400,DateUtil.currentDate(),"falure",null);
			return ResponseEntity.status(400).body(apiResponse);
		}
	}
	
}
