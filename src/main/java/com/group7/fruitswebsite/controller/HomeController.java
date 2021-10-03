package com.group7.fruitswebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "index", "home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("action","index");
		model.addAttribute("menu","menu");
		return "client/index";
	}

	@GetMapping("/shop-grid")
	public String shop(Model model) {
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
	public String shop_details() {
		return "client/shop-details";
	}

	@GetMapping("/shoping-cart")
	public String shoping_cart() {
		return "client/shoping-cart";
	}
	
	@GetMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("action","blog");
		return "client/blog";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("action","contact");
		return "client/contact";
	}

}
