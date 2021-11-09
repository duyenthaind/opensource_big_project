package com.group7.fruitswebsite.service.search.impl;

import com.group7.fruitswebsite.dto.search.condition.BlogCondition;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhBlog;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.service.search.BlogSearchService;
import com.group7.fruitswebsite.util.QueryGeneratorUtil;
import com.group7.fruitswebsite.util.QueryHelper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author duyenthai
 */
@Log4j
@Service
public class BlogSearchServiceImpl implements BlogSearchService {
    private EntityManager entityManager;

    @Override
    public Result<DhBlog> search(List<BlogCondition> conditions, int page) {
        try {
            String sqlQuery = QueryGeneratorUtil.generateQuery(conditions, "dh_blog");
            log.info(String.format("Query sqlQuery: %s", sqlQuery));
            if (!sqlQuery.equals(StringUtils.EMPTY)) {
                Result<DhBlog> result = QueryHelper.replaceConditionAndQuery(conditions, sqlQuery, entityManager, DhBlog.class, page);
                return result;
            }
        } catch (Exception ex) {
            log.error("Search error, ", ex);
        }
        return new Result<>();
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
