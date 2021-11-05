package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.dto.search.Condition;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author duyenthai
 */
public class QueryHelper {
    private QueryHelper() {
    }

    public static List replaceConditionAndQuery(List<?> conditions, String sqlQuery, EntityManager entityManager, Class clazz) {
        Query nativeQuery = entityManager.createNativeQuery(sqlQuery, clazz);
        conditions.forEach(val -> {
            if (val instanceof Condition) {
                Condition condition = (Condition) val;
                nativeQuery.setParameter(condition.getKey(), condition.getValue());
            }
        });
        return nativeQuery.getResultList();
    }
}
