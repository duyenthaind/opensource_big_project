package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface OrderProductRepository extends JpaRepository<DhOrderProduct, Integer> {
}
