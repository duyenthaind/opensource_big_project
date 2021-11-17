package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.customupdate.UserCustomUpdateRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface UserRepository extends JpaRepository<DhUser, Integer>, UserCustomUpdateRepository {
    Optional<DhUser> findByUsername(String userName);
    Page<DhUser> findAll(Pageable pageable);
}
