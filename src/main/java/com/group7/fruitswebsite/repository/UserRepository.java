package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface UserRepository extends JpaRepository<DhUser, Integer> {
    Optional<DhUser> findByUsername(String userName);
}
