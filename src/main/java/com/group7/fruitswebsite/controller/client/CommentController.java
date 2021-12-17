package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhCommentModel;
import com.group7.fruitswebsite.service.CommentService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.SecurityUtil;

import lombok.extern.log4j.Log4j;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author duyenthai
 */
@RestController(value = "commentClientController")
@RequestMapping("/v1/api/client-comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log4j
public class CommentController {

	private CommentService commentService;

//    @GetMapping("/comments")
//    public ResponseEntity<ApiResponse> getAllCommentsForProduct(@RequestParam(defaultValue = "0") int page,
//                                                                @RequestParam(defaultValue = "5") int size, @RequestParam Integer productId) {
//        if (productId == null) {
//            log.info("Drop all action get comments because it is for no product");
//            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_IN_COMMENT_NOT_DEFINED, HttpStatus.EXPECTATION_FAILED);
//        }
//        return commentService.getAllForProductWithPaging(productId, page, size);
//    }

	@GetMapping("/comments")
	public ResponseEntity<ApiResponse> getAllCommentsForProduct(@RequestParam Integer productId) {
		if (productId == null) {
			log.info("Drop all action get comments because it is for no product");
			return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_IN_COMMENT_NOT_DEFINED,
					HttpStatus.EXPECTATION_FAILED);
		}
		return commentService.getAll(productId);
	}

	@PostMapping("/comments")
	public ResponseEntity<ApiResponse> saveOne(@RequestBody DhCommentModel dhCommentModel) {
		if (dhCommentModel.getProductId() == null) {
			log.info(String.format("Drop all action for comment %s because it is for no product", dhCommentModel));
			return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_IN_COMMENT_NOT_DEFINED,
					HttpStatus.FORBIDDEN);
		}
		UserDetails details = SecurityUtil.getUserDetails();
		if (Objects.nonNull(details)) {
			return commentService.saveOne(dhCommentModel, details.getUsername());
		}
		return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ACCOUNT_IS_NOT_FOUND,
				HttpStatus.FORBIDDEN);
	}

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}
