package com.group7.fruitswebsite.repository;

import java.util.List;

import com.group7.fruitswebsite.entity.DhBlog;

public interface CustomBlogRepository {
	List<DhBlog> getLimit(int limit);
}
