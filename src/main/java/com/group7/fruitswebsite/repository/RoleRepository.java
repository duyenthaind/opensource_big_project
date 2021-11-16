package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface RoleRepository extends JpaRepository<DhRole, Integer> {

    Optional<DhRole> findByName(String name);
}
