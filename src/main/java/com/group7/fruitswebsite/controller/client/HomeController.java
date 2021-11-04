package com.group7.fruitswebsite.controller.client;

import javax.servlet.http.HttpServletRequest;

import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;

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

	@RequestMapping(value = {"/", "index", "home"}, method = RequestMethod.GET)
    public String home(Model model,HttpServletRequest request) {
        model.addAttribute("action", "index");
        model.addAttribute("menu", "menu");
        model.addAttribute("categories", categoryService.getAllEntity());
        model.addAttribute("products", productService.getAllProductsAsDto());
//        model.addAttribute("blogs",blogService.getTop3BlogsAsDto());
        request.setAttribute("blogs", blogService.getTop3BlogsAsDto());
        return "client/index";
    }

    @GetMapping("/shop-grid")
    public String shop(Model model, HttpServletRequest request) {
        model.addAttribute("categories", categoryService.getAllEntity());
        if (request.getParameter("categoryId") != null) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));

        } else {
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
