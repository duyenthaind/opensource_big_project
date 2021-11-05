package com.group7.fruitswebsite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;

@Repository
public interface ProductRepository extends JpaRepository<DhProduct, Integer>,CustomProductRepository{
    Page<DhProduct> findAll(Pageable pageable);
    
    Page<DhProduct> findByCategory(Pageable pageable,DhCategory category);

    @Query(value = "SELECT count(*) FROM Dh_Product WHERE category_id = ?1",nativeQuery = true)
    Integer findByCategory(Integer categoryId);
}
