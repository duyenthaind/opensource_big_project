package com.group7.fruitswebsite.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.service.ProductImageService;
import com.group7.fruitswebsite.service.ProductService;
import com.group7.fruitswebsite.service.impl.ImageProductServiceImpl;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.StringUtil;

import lombok.extern.log4j.Log4j;


@Controller(value = "productAdminController")
@Log4j
@RequestMapping(value = "/api/product/v1")
public class ProductController {
	
    private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ApiResponse> addNewProduct(@ModelAttribute DhProductModel dhProductModel){
		log.debug(dhProductModel.toString());
		ImageService imageService = new ImageProductServiceImpl();
		List<String> imagePath = imageService.saveUploadedMultiFiles(dhProductModel.getFiles());
		dhProductModel.setPathUploadedAvatar(imagePath);
		return productService.saveOne(dhProductModel);
	}

	@GetMapping("/products")
	public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		return productService.getAllWithPaging(page, size);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
		return productService.getOne(id);
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	
	
}
