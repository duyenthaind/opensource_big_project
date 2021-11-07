package com.group7.fruitswebsite.service.search;

import java.util.List;

import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.search.condition.ProductCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhProduct;

/**
 * @author duyenthai
 */
public interface SearchService<T,Y> {
    T search(List<Y> conditions, int page);
}
