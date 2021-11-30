package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
public interface CartRepository extends JpaRepository<DhCart, Integer> {
    List<DhCart> findByUserId(Integer userId);

    Optional<DhCart> findByUserIdAndProductId(Integer userId, Integer productId);
}
