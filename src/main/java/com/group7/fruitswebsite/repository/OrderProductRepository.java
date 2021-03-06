package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author duyenthai
 */
public interface OrderProductRepository extends JpaRepository<DhOrderProduct, Integer> {

    @Query(value = "from DhOrderProduct where order.id = :orderId")
    List<DhOrderProduct> findByOrderId(@Param("orderId") Integer orderId);

    @Query(value = "delete from DhOrderProduct where order.id = :orderId")
    @Transactional
    @Modifying
    void deleteByOrderId(@Param("orderId") Integer orderId);
}
