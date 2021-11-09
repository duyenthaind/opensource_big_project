package com.group7.fruitswebsite.service.search;

import com.group7.fruitswebsite.dto.search.condition.BlogCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhBlog;

/**
 * @author duyenthai
 */
public interface BlogSearchService extends SearchService<Result<DhBlog>, BlogCondition> {
}
