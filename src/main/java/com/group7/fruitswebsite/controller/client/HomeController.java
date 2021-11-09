package com.group7.fruitswebsite.controller.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group7.fruitswebsite.common.search.Operator;
import com.group7.fruitswebsite.dto.search.condition.ProductCondition;
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
                       @RequestParam(defaultValue = "12") int size, @RequestParam(required = false) String searchText,
                       @RequestParam(required = false) String typeSearch, @RequestParam(required = false, defaultValue = "-1") Integer categoryId) {
        model.addAttribute("categories", categoryService.getAllEntity());
        model.addAttribute("top9Products", productService.getTopRandomProductsAsDto(3));
        model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(3));
        model.addAttribute("prodOrBPriceSale", productService.getProductsOrderByPriceSaleAscAsDto());

        if (categoryId == -1 && StringUtils.isEmpty(searchText)) {
            model.addAttribute("categoryId", categoryId);

            model.addAttribute("allProducts", productService.getAllProductsDtoWithPaging(page, size));
            model.addAttribute("totalPages", productService.getTotalPagesByCategory(size, -1));

        } else {
            // mapping conditions
            // add conditions to search
            List<ProductCondition> conditions = new ArrayList<>();
            // check condition name
            if (!StringUtils.isEmpty(searchText)) {
                ProductCondition condition1 = new ProductCondition();
                condition1.setKey(Constants.Search.Product.NAME);
                condition1.setValue(searchText);
                condition1.setOperator(Operator.LIKE);
                conditions.add(condition1);
            }
            // check category
            if (categoryId > -1) {
                ProductCondition condition2 = new ProductCondition();
                condition2.setKey(Constants.Search.Product.CATEGORY_ID);
                condition2.setValue(categoryId);
                condition2.setOperator(Operator.EQUAL);
                conditions.add(condition2);
            }
            // put conditions to FE, marked as this result is from searched, not fetched
            model.addAttribute("conditions", conditions);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("searchText", searchText);
            Result<DhProductDto> searchedResults = productService.searchProduct(conditions, page);
            model.addAttribute("productSearch", searchedResults.getDatas());
            model.addAttribute("totalPages", searchedResults.getListPages());
        }

        return "client/shop-grid";
    }

    @GetMapping("/shop-grid-search")
    public String searchProduct(Model model, @RequestParam(defaultValue = "0") int page,
                                @RequestParam String searchText, @RequestParam Integer categoryId) {
        model.addAttribute("categories", categoryService.getAllEntity());
        model.addAttribute("top9Products", productService.getTopRandomProductsAsDto(3));
        model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(3));
        model.addAttribute("prodOrBPriceSale", productService.getProductsOrderByPriceSaleAscAsDto());

        List<ProductCondition> conditions = new ArrayList<>();
        if (!StringUtils.isEmpty(searchText)) {
            ProductCondition condition1 = new ProductCondition();
            condition1.setKey(Constants.Search.Product.NAME);
            condition1.setValue(searchText);
            condition1.setOperator(Operator.LIKE);
            conditions.add(condition1);
        }
        if (categoryId > -1) {
            ProductCondition condition2 = new ProductCondition();
            condition2.setKey(Constants.Search.Product.CATEGORY_ID);
            condition2.setValue(categoryId);
            condition2.setOperator(Operator.EQUAL);
            conditions.add(condition2);
        }
        model.addAttribute("conditions", conditions);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("searchText", searchText);
        Result<DhProductDto> searchedResults = productService.searchProduct(conditions, page);
        model.addAttribute("productSearch", searchedResults.getDatas());
        model.addAttribute("totalPages", searchedResults.getListPages());
        return "client/shop-grid";
    }

    @GetMapping("/blog-details")
    public String blog_details(Model model,@RequestParam("blogId") Integer blogId) {
    	model.addAttribute("blog",blogService.getOneBlogAsDto(blogId));
    	model.addAttribute("blogs", blogService.getTopBlogsAsDto(3));
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
