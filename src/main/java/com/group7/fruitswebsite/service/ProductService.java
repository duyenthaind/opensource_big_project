package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.search.condition.ProductCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.model.DhProductModel;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ApiResponse> saveOne(DhProductModel dhProductModel);

    ResponseEntity<ApiResponse> getAll();

    List<DhProductDto> getAllProductsAsDto();

    ResponseEntity<ApiResponse> getAllWithPaging(int page, int size);

    ResponseEntity<ApiResponse> getOne(int id);

    DhProductDto getOneProductsAsDto(Integer id);

    ResponseEntity<ApiResponse> update(DhProductModel dhProductModel);

    ResponseEntity<ApiResponse> delete(int id);

    List<DhProductDto> getTopRandomProductsAsDto(int limit);

    List<DhProductDto> getProductsInListCategoryAsDto(int total);

    List<DhProductDto> getProductsOrderByPriceSaleAscAsDto();

    List<DhProductDto> getProductsByCategoryIdWithPaging(int page, int size, Integer categoryId);

    List<DhProductDto> getAllProductsDtoWithPaging(int page, int size);

    List<Integer> getTotalPagesByCategory(int size, int categoryId);

    ResponseEntity<ApiResponse> search(List<ProductCondition> conditions, int page);

    Result<DhProductDto> searchProduct(List<ProductCondition> conditions, int page);
}
