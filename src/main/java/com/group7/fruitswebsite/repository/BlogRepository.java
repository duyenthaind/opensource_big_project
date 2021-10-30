package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface BlogRepository extends JpaRepository<DhBlog, Integer> {
    Page<DhBlog> findAll(Pageable pageable);

}
