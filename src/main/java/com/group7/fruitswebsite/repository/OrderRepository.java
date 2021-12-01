package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.repository.customupdate.OrderCustomUpdateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author duyenthai
 */
public interface OrderRepository extends JpaRepository<DhOrder, Integer>, OrderCustomUpdateRepository {
    @Query("from DhOrder where dhUser.id = :userId")
    List<DhOrder> findByUserId(@Param("userId") Integer userId);

    Page<DhOrder> findAll(Pageable pageable);
}
