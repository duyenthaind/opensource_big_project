package com.group7.fruitswebsite.controller;

import com.group7.fruitswebsite.common.Constants;
import lombok.extern.log4j.Log4j;
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
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.StringUtil;

@Controller
@RequestMapping(value = "/admin")
@Log4j
public class AdminController {

    private CategoryRepository categoryRepository;

    @GetMapping(value = {"/index", "/", "/home", ""})
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
    public ResponseEntity<ApiResponse> addNewCate(@RequestBody DhCategory category) {
        log.info(category.toString());
        ApiResponse apiResponse;
        try {
            category.setSeo(StringUtil.seo(category.getName()));
            categoryRepository.save(category);
            apiResponse = new ApiResponse(Constants.APIResponseStatus.SUCCESS_200.getStatus(),
                    DateUtil.currentDate(), Constants.APIResponseStatus.SUCCESS_200.getMessage(), category);
            return ResponseEntity.status(Constants.APIResponseStatus.SUCCESS_200.getStatus()).body(apiResponse);
        } catch (Exception e) {
            // TODO: handle exception
            apiResponse = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(),
                    DateUtil.currentDate(), Constants.APIResponseStatus.FAILURE.getMessage(), null);
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(apiResponse);
        }
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
