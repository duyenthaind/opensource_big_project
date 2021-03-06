package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhBlogDto;
import com.group7.fruitswebsite.dto.search.condition.BlogCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
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
    
    List<DhBlogDto> getTopBlogsAsDto(int limit);

    List<DhBlogDto> getMostRecentBlogsAsDto(int limit);

    Integer countAll();

    Result<DhBlogDto> searchBlog(List<BlogCondition> conditions, int page);

}
