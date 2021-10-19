package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duyenthai
 */
public interface ProductImageRepository extends JpaRepository<DhProductImage, Integer> {

    DhProductImage getByPath(String path);
}
