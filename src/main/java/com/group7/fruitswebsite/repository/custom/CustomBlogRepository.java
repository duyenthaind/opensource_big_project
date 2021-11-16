package com.group7.fruitswebsite.repository.custom;

import java.util.List;

import com.group7.fruitswebsite.entity.DhBlog;

public interface CustomBlogRepository {
	List<DhBlog> getLimit(int limit);

	List<DhBlog> getMostRecentBlogs(int limit);
}
