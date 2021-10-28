package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author duyenthai
 */
public interface ProductImageRepository extends JpaRepository<DhProductImage, Integer> {

    DhProductImage getByPath(String path);

    List<DhProductImage> getByDhProductId(int dhProductId);

    @Query(value = "delete from DhProductImage where id not in :ids and dhProduct.id = :productId")
    @Transactional
    @Modifying
    void deleteOldImageFromProduct(@Param("ids") List<Integer> ids,@Param("productId") int productId);

    @Query(value = "delete from DhProductImage where dhProduct.id = :id")
    @Transactional
    @Modifying
    void deleteByDhProductId(@Param("id") int productId);
}
