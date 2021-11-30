package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface OrderRepository extends JpaRepository<DhOrder, Integer> {
}
