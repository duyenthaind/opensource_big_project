package com.group7.fruitswebsite.service.search.impl;

import com.group7.fruitswebsite.dto.search.condition.ProductCondition;
import com.group7.fruitswebsite.dto.search.result.ProductSearchResult;
import com.group7.fruitswebsite.dto.search.result.Result;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.service.search.ProductSearchService;
import com.group7.fruitswebsite.util.QueryGeneratorUtil;
import com.group7.fruitswebsite.util.QueryHelper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class ProductSearchImpl implements ProductSearchService {

    private EntityManager entityManager;

    @Override
    public ProductSearchResult search(List<ProductCondition> conditions, int page) {
        try {
            String sqlQuery = QueryGeneratorUtil.generateQuery(conditions, "dh_product");
            log.info(String.format("Query sqlQuery: %s", sqlQuery));
            if (!sqlQuery.equals(StringUtils.EMPTY)) {
                Result<DhProduct> result = QueryHelper.replaceConditionAndQuery(conditions, sqlQuery, entityManager, DhProduct.class, page);
                ProductSearchResult res = new ProductSearchResult();
                res.setTotal(result.getTotal());
                res.setDatas(result.getDatas());
                return res;
            }
        } catch (Exception ex) {
            log.error("Search error, ", ex);
        }
        return new ProductSearchResult();
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
