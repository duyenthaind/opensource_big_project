package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhBlog;
import com.group7.fruitswebsite.model.DhBlogModel;
import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.service.impl.img.ImageBlogServiceImpl;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author duyenthai
 */
@RestController(value = "blogControllerAdmin")
@Log4j
@RequestMapping("/api/blog/v1")
public class BlogController {

    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<ApiResponse> getAllBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return blogService.getAllWithPaging(page, size);
    }

    @PostMapping("/blogs")
    public ResponseEntity<ApiResponse> add(@ModelAttribute DhBlogModel dhBlogModel) {
        log.info(dhBlogModel);
        ImageService imageService = new ImageBlogServiceImpl();
        String imagePath = imageService.saveUploadFiles(dhBlogModel.getFiles());
        log.info(String.format("uploaded image to system: %s", imagePath));
        dhBlogModel.setAvatar(imagePath);
        return blogService.saveOne(dhBlogModel);
    }

    @PutMapping("/blogs")
    public ResponseEntity<ApiResponse> update(@ModelAttribute DhBlogModel dhBlogModel) {
        log.debug(dhBlogModel);
        if (dhBlogModel.getId() == null) {
            log.error(String.format("Drop all action with model %s because it has no id", dhBlogModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.BLOG_ID_IS_NOT_DEFINED, HttpStatus.FORBIDDEN);
        }
        MultipartFile[] files = dhBlogModel.getFiles();
        if (files != null && files.length > 0) {
            ImageService imageService = new ImageBlogServiceImpl();
            MultipartFile file = files[0];
            Optional<DhBlog> optional = imageService.checkExists(file, dhBlogModel.getId());
            if (optional.isPresent()) {
                dhBlogModel.setAvatar(optional.get().getAvatar());
                // reset the uploaded files if file is exists
                files = new MultipartFile[1];
            }
            String imagePath = imageService.saveUploadFiles(files);
            if (!StringUtils.isEmpty(imagePath)) {
                dhBlogModel.setAvatar(imagePath);
            }
        }
        return blogService.update(dhBlogModel);
    }

    @DeleteMapping("/blogs")
    public ResponseEntity<ApiResponse> delete(@RequestParam Integer id) {
        return blogService.delete(id);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id) {
        return blogService.getOne(id);
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}
