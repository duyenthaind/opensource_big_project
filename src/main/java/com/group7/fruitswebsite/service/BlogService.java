package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhBlogDto;
import com.group7.fruitswebsite.model.DhBlogModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author duyenthai
 */
public interface BlogService {
    ResponseEntity<ApiResponse> saveOne(DhBlogModel dhBlogModel);

    ResponseEntity<ApiResponse> getAll();

    List<DhBlogDto> getAllBlogsAsDto();

    ResponseEntity<ApiResponse> getAllWithPaging(int page, int size);

    ResponseEntity<ApiResponse> getOne(int id);

    DhBlogDto getOneBlogAsDto(int id);

    ResponseEntity<ApiResponse> update(DhBlogModel dhBlogModel);

    ResponseEntity<ApiResponse> delete(int id);
}
