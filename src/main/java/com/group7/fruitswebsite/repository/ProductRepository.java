package com.group7.fruitswebsite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group7.fruitswebsite.entity.DhProduct;

@Repository
public interface ProductRepository extends JpaRepository<DhProduct, Integer>{
    Page<DhProduct> findAll(Pageable pageable);
    
    @Query(value = "SELECT * FROM DhProduct WHERE category_id =: categoryId",nativeQuery = true)
    List<DhProduct> findAllByCategoryId(Integer categoryId);
    
    @Query("select p from DhProduct p order by RAND()")
    public List<DhProduct> findRandamQuestions();
}
