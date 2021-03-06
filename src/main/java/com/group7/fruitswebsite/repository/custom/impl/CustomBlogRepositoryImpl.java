package com.group7.fruitswebsite.repository.custom.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.group7.fruitswebsite.entity.DhBlog;
import com.group7.fruitswebsite.repository.custom.CustomBlogRepository;

import lombok.extern.log4j.Log4j;

@Log4j
@Transactional
public class CustomBlogRepositoryImpl implements CustomBlogRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DhBlog> getLimit(int limit) {
        String jpql = "SELECT p FROM DhBlog p ORDER BY p.thumbnail";
        List<DhBlog> blogs = null;
        try {
            blogs = entityManager.createQuery(jpql, DhBlog.class).setMaxResults(limit).getResultList();
        } catch (Exception e) {
            log.error("Get top blog error, ", e);
        }
        return blogs;
    }

    @Override
    public List<DhBlog> getMostRecentBlogs(int limit) {
        String jpql = "select b from DhBlog b order by b.createdDate desc";
        try {
            return entityManager.createQuery(jpql, DhBlog.class).setMaxResults(limit).getResultList();
        } catch (Exception ex) {
            log.error("Error get most recent blogs, ", ex);
        }
        return Collections.emptyList();
    }


}
