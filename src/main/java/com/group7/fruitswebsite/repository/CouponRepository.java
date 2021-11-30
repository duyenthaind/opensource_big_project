package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface CouponRepository extends JpaRepository<DhCoupon, Integer> {
    Optional<DhCoupon> findByCode(String code);
}
