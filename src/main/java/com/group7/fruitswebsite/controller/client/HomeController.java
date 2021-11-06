package com.group7.fruitswebsite.controller.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.search.ProductCondition;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;
import com.group7.fruitswebsite.util.StringUtil;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class HomeController {

	private CategoryService categoryService;
	private ProductService productService;
	private BlogService blogService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = { "/", "index", "home" }, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("action", "index");
		model.addAttribute("menu", "menu");
		model.addAttribute("categories", categoryService.getAllEntity());
		model.addAttribute("products", productService.getProductsInListCategoryAsDto(4));
		model.addAttribute("blogs", blogService.getTopBlogsAsDto(3));
		model.addAttribute("top9Products", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("top9Products2", productService.getTopRandomProductsAsDto(3));
		return "client/index";
	}

	@GetMapping("/shop-grid")
	public String shop(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "12") int size,@RequestParam String searchText,
			@RequestParam String typeSearch) {
		model.addAttribute("categories", categoryService.getAllEntity());
		model.addAttribute("top9Products", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("prodOrBPriceSale", productService.getProductsOrderByPriceSaleAscAsDto());
		
		List<ProductCondition> conditions = new ArrayList<ProductCondition>();
		if (!StringUtil.isNullOrEmpty(request.getParameter("categoryId"))) {
			Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
			model.addAttribute("categoryId", categoryId);
			
			model.addAttribute("productByCategoryWithPage",
					productService.getProductsByCategoryIdWithPaging(page, size, categoryId));

			int countPage = productService.getProductsByCategoryIdWithPaging(page, size, categoryId).size();
			model.addAttribute("totalProductByCategoryWithpage", countPage);
			model.addAttribute("totalPages", productService.getTotalPagesByCategory(size, categoryId));
		}else {
			if (!StringUtil.isNullOrEmpty(searchText)) {
				model.addAttribute("productSearch", productService.search(conditions));
			}
		}
		return "client/shop-grid";
	}

	@PostMapping("/shop-grid/search")
	public String searchProduct(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "12") int size, @RequestParam String searchText,
			@RequestParam String typeSearch) {
		model.addAttribute("categories", categoryService.getAllEntity());
		model.addAttribute("top9Products", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(3));
		model.addAttribute("prodOrBPriceSale", productService.getProductsOrderByPriceSaleAscAsDto());
		
		List<ProductCondition> conditions = new ArrayList<ProductCondition>();
		if (!StringUtil.isNullOrEmpty(searchText)) {
			model.addAttribute("productSearch", productService.search(conditions));
		}
		return "client/shop-grid";
	}

	@GetMapping("/blog-details")
	public String blog_details() {
		return "client/blog-details";
	}

	@GetMapping("/checkout")
	public String checkout() {
		return "client/checkout";
	}

	@GetMapping("/shop-details")
	public String shop_details(@RequestParam Integer productId, Model model) {
		model.addAttribute("product", productService.getOneProductsAsDto(productId));
		model.addAttribute("productImages", productService.getOneProductsAsDto(productId).getProductImages());
		return "client/shop-details";
	}

	@GetMapping("/shoping-cart")
	public String shoping_cart() {
		return "client/shoping-cart";
	}

	@GetMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("action", "blog");
		return "client/blog";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("action", "contact");
		return "client/contact";
	}

}
