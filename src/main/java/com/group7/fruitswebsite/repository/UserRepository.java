package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface UserRepository extends JpaRepository<DhUser, Integer> {
}
