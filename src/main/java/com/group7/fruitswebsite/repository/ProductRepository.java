package com.group7.fruitswebsite.repository;

import java.util.List;

import com.group7.fruitswebsite.repository.custom.CustomProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;

@Repository
public interface ProductRepository extends JpaRepository<DhProduct, Integer>, CustomProductRepository {
    Page<DhProduct> findAll(Pageable pageable);

    Page<DhProduct> findByName(Pageable pageable, String name);

    Page<DhProduct> findByCategory(Pageable pageable, DhCategory category);

    @Query(value = "SELECT * FROM dh_product WHERE price > 0 and price <= ?1", nativeQuery = true)
    Page<DhProduct> findByPrice(Long price, Pageable pageable);

    @Query(value = "SELECT count(*) FROM dh_product WHERE category_id = ?1", nativeQuery = true)
    Integer findNumsByCategory(Integer categoryId);

    @Query(value = "SELECT * FROM dh_product p WHERE p.name LIKE %:name%", nativeQuery = true)
    List<DhProduct> findAllByName(@Param("name") String name);
}
