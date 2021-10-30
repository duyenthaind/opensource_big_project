package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhBlogDto;
import com.group7.fruitswebsite.entity.DhBlog;
import com.group7.fruitswebsite.model.DhBlogModel;
import com.group7.fruitswebsite.repository.BlogRepository;
import com.group7.fruitswebsite.service.BlogService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhBlogModel dhBlogModel) {
        try {
            DhBlog dhBlog = mapBlogFromModel(dhBlogModel);
            dhBlog.setCreatedDate(System.currentTimeMillis());
            blogRepository.save(dhBlog);
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Error insert new blog, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAll() {
        try {
            List<DhBlogDto> listAllBlogs = getAllBlogsAsDto();
            ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
            apiResponseResult.setData(listAllBlogs);
            return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);
        } catch (Exception ex) {
            log.error("Error get all blogs, ", ex);
        }
        return ApiResponseUtil.getBaseFailureStatus();
    }

    @Override
    public List<DhBlogDto> getAllBlogsAsDto() {
        try {
            List<DhBlog> listAllBlogs = blogRepository.findAll();
            return listAllBlogs.stream().map(t -> DtoUtil.getBlogDtoFromDhBlog(t, objectMapper)).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error get all blogs as dto, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DhBlog> pageBlogs = blogRepository.findAll(pageable);
            List<DhBlogDto> blogDtos = pageBlogs.getContent().stream()
                    .map(val -> DtoUtil.getBlogDtoFromDhBlog(val, objectMapper)).collect(Collectors.toList());
            int totalBlogs = blogRepository.findAll().size();
            ApiResponse.ApiResponseResult apiResponseResult = ApiResponseUtil.mapResultFromList(blogDtos, pageBlogs, totalBlogs, size);
            return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);
        } catch (Exception ex) {
            log.error("Get all blogs with paging error", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getOne(int id) {
        try {
            DhBlogDto dto = getOneBlogAsDto(id);
            ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
            apiResponseResult.setData(Collections.singletonList(dto));
            return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);
        } catch (Exception ex) {
            log.error(String.format("Get one blog with id %s error", id), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public DhBlogDto getOneBlogAsDto(int id) {
        try {
            Optional<DhBlog> optional = blogRepository.findById(id);
            DhBlog dhBlog = optional.orElseGet(DhBlog::new);
            return DtoUtil.getBlogDtoFromDhBlog(dhBlog, objectMapper);
        } catch (Exception ex) {
            log.error("Get one blog as dto error, ", ex);
        }
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(DhBlogModel dhBlogModel) {
        try {
            DhBlog dhBlog = mapBlogFromModel(dhBlogModel);
            blogRepository.save(dhBlog);

            log.info(String.format("Update 1 blog with id=%s", dhBlog.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Error update blog with id %s error, ", dhBlogModel.getId()), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> delete(int id) {
        try {
            blogRepository.deleteById(id);
            log.info(String.format("Delete 1 blog, id=%d", id));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Delete blog with id %s error", id), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    public DhBlog mapBlogFromModel(DhBlogModel dhBlogModel) throws JsonProcessingException {
        String modelJson = objectMapper.writeValueAsString(dhBlogModel);
        return objectMapper.readValue(modelJson, DhBlog.class);
    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
