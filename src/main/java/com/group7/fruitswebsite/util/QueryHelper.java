package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.search.Operator;
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
                Object value = condition.getOperator().equals(Operator.LIKE) ? String.format("%%%s%%", condition.getValue()) : condition.getValue();
                nativeQuery.setParameter(condition.getKey(), value);
            }
        });
        return nativeQuery.getResultList();
    }
}
