package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.common.search.Operator;
import com.group7.fruitswebsite.dto.search.condition.Condition;
import com.group7.fruitswebsite.dto.search.result.Result;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author duyenthai
 */
public class QueryHelper {
    private QueryHelper() {
    }

    public static Result replaceConditionAndQuery(List<?> conditions, String sqlQuery, EntityManager entityManager, Class clazz, int page) {
        Query nativeQuery = entityManager.createNativeQuery(sqlQuery, clazz);
        conditions.forEach(val -> {
            if (val instanceof Condition) {
                Condition condition = (Condition) val;
                Object value = condition.getOperator().equals(Operator.LIKE) ? String.format("%%%s%%", condition.getValue()) : condition.getValue();
                nativeQuery.setParameter(condition.getKey(), value);
            }
        });
        Result result = new Result();
        result.setTotal(nativeQuery.getResultList().size());
        int firstResult = page > 0 ? (page - 1) * Constants.Search.SEARCH_PER_PAGE : 0;
        nativeQuery.setFirstResult(firstResult);
        nativeQuery.setMaxResults(Constants.Search.SEARCH_PER_PAGE);
        result.setDatas(nativeQuery.getResultList());
        return result;
    }
}
