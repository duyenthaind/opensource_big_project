package com.group7.fruitswebsite.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.DhBlogDto;
import com.group7.fruitswebsite.dto.DhCartDto;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.search.condition.BlogCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.service.CartService;
import com.group7.fruitswebsite.service.CategoryService;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.PagingUtil;
import com.group7.fruitswebsite.util.SecurityUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;
    private CartService cartService;
  
    @Autowired
    public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public String blog_details(Model model,@RequestParam Integer id) {
    	model.addAttribute("blog",blogService.getOneBlogAsDto(id));
    	model.addAttribute("blogs", blogService.getTopBlogsAsDto(3));
        model.addAttribute("mostRecentBlogs", blogService.getTopBlogsAsDto(Constants.Search.Blog.NUM_BLOGS_AT_SIDE_BAR));
        return "client/blog-details";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
    	User currentUser = SecurityUtil.getUserDetails();
        if (currentUser != null) {
            Optional<DhUser> user = userService.findByUserName(currentUser.getUsername());
            if (user.isPresent()) {
            	model.addAttribute("userOrder",user.get());
            	List<DhCartDto> listCartDtos = cartService.findAllCart(currentUser.getUsername());
            	model.addAttribute("cartOrder",listCartDtos);
            }  
        }
        return "client/checkout";
    }

    @GetMapping("/shop-details")
    public String shop_details(@RequestParam Integer productId, Model model) {
    	model.addAttribute("productIdForComment",productId);
    	model.addAttribute("top9Products1", productService.getTopRandomProductsAsDto(4));
        model.addAttribute("product", productService.getOneProductsAsDto(productId));
        model.addAttribute("productImages", productService.getOneProductsAsDto(productId).getProductImages());
        return "client/shop-details";
    }

    @GetMapping("/shoping-cart")
    public String shoping_cart() {
        return "client/shoping-cart";
    }
    
    @GetMapping("/liked")
    public String liked() {
        return "client/liked";
    }
    
    @GetMapping("/purchase-result")
    public String purchase() {
        return "dialog/purchase-success";
    }
    
    @GetMapping("/403")
    public String permission() {
        return "dialog/403";
    }
    
    @GetMapping("/404")
    public String page_not_found() {
        return "dialog/404";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("action", "blog");
        model.addAttribute("mostRecentBlogs", blogService.getTopBlogsAsDto(Constants.Search.Blog.NUM_BLOGS_AT_SIDE_BAR));
        model.addAttribute("displayBlogs", blogService.getMostRecentBlogsAsDto(Constants.Search.Blog.BLOGS_PER_PAGE));
        model.addAttribute("listPageIndexes",
                PagingUtil.generateListPagingFromDataAndSize(blogService.countAll(), Constants.Search.Blog.BLOGS_PER_PAGE));

        return "client/blog";
    }

    @GetMapping("/blog/search")
    public String blogSearch(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String textSearch) {
        model.addAttribute("action", "blog");
        model.addAttribute("mostRecentBlogs", blogService.getTopBlogsAsDto(Constants.Search.Blog.NUM_BLOGS_AT_SIDE_BAR));

        List<BlogCondition> conditions = new ArrayList<>();
        if (!StringUtils.isEmpty(textSearch)) {
            model.addAttribute("textSearch", textSearch);
            BlogCondition condition = new BlogCondition();
            condition.setKey(Constants.Search.Blog.THUMBNAIL);
            condition.setValue(textSearch);
            condition.setOperator(Operator.LIKE);
            conditions.add(condition);
        }

        Result<DhBlogDto> searchResult = blogService.searchBlog(conditions, page);
        model.addAttribute("displayBlogs", searchResult.getDatas());
        model.addAttribute("listPageIndexes", searchResult.getListPages());
        return "client/blog";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("action", "contact");
        return "client/contact";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model, @RequestParam String username) {
        model.addAttribute("user",userService.getUserByUsernameAsDto(username));
        return "client/user-profile";
    }

}
