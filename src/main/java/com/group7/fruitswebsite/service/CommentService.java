package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhCommentModel;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface CommentService {
    ResponseEntity<ApiResponse> saveOne(DhCommentModel dhCommentModel);

    ResponseEntity<ApiResponse> getAll();

    ResponseEntity<ApiResponse> getAllForProductWithPaging(int productId, int page, int size);
}
