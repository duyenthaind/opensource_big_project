package com.group7.fruitswebsite.service.search;

import com.group7.fruitswebsite.dto.search.condition.ProductCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhProduct;

/**
 * @author duyenthai
 */
public interface ProductSearchService extends SearchService<Result<DhProduct>, ProductCondition> {
}
