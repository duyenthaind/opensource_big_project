package com.group7.fruitswebsite.service.search.impl;

import com.group7.fruitswebsite.dto.search.Condition;
import com.group7.fruitswebsite.dto.search.ProductCondition;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.service.search.ProductSearchService;
import com.group7.fruitswebsite.util.QueryGeneratorUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public List<DhProduct> search(List<ProductCondition> conditions) {
        try {
            String sqlQuery = QueryGeneratorUtil.generateQuery(conditions, "dh_product");
            log.info(String.format("Query sqlQuery: %s", sqlQuery));
            if (!sqlQuery.equals(StringUtils.EMPTY)) {
                Query nativeQuery = entityManager.createNativeQuery(sqlQuery, DhProduct.class);
                conditions.forEach(val -> {
                    nativeQuery.setParameter(val.getKey(), val.getValue());
                });
                return nativeQuery.getResultList();
            }
        } catch (Exception ex) {
            log.error("Search error, ", ex);
        }
        return Collections.emptyList();
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
