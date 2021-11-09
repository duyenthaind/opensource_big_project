package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhBlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author duyenthai
 */
public interface BlogRepository extends JpaRepository<DhBlog, Integer>,CustomBlogRepository {
    Page<DhBlog> findAll(Pageable pageable);

    @Query(value = "select count(b) from DhBlog b")
    Integer countAll();
}
