package com.group7.fruitswebsite.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group7.fruitswebsite.entity.DhUserProduct;

public interface UserProductRepository extends JpaRepository<DhUserProduct, Long>{
    @Query(value = "select * from dh_user_product where user_id = :userId", nativeQuery = true)
    List<DhUserProduct> findByUserId(@Param("userId") Integer userId);
    
    @Query(value = "select * from dh_user_product where product_id = :productId", nativeQuery = true)
    Optional<DhUserProduct> findByProductId(@Param("productId") Integer productId);
    
    @Query(value = "delete from dh_user_product where user_id = :userId and product_id = :productId", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByUserIdAndProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);
}
