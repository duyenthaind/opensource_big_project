package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
public interface ProductImageService {
    ResponseEntity<ApiResponse> saveOne(DhProductImage dhProductImage);

    Optional<DhProductImage> getById(int id);

    Optional<DhProductImage> getByPath(String path);

    void deleteOldImageFromProduct(List<DhProductImage> productImages, int productId);

    ResponseEntity<ApiResponse> delete(int id);
}
