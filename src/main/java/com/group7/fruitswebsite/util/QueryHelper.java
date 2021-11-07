package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.common.search.Operator;
import com.group7.fruitswebsite.dto.search.condition.Condition;
import com.group7.fruitswebsite.dto.search.condition.ProductSearchDto;
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
            	Object value = null;
            	Condition condition = (Condition) val;
            	if(condition.getValue().getCategoryId() > 0 && condition.getValue().getCategoryId() != null) {	
            		value =  String.format("%%%s%%", condition.getValue().getSearchText());
                    nativeQuery.setParameter("category_id", condition.getValue().getCategoryId());
                    nativeQuery.setParameter("name", value);
            	}else {
            		 value = condition.getOperator().equals(Operator.LIKE) ? String.format("%%%s%%", condition.getValue().getSearchText()) : condition.getValue().getSearchText();
                     nativeQuery.setParameter(condition.getKey(), value);
            	}
               
            }
        });
        Result result = new Result();
        result.setTotal(nativeQuery.getResultList().size());
        result.setTotalPages(Constants.Search.SEARCH_PER_PAGE);
        result.setListPages();
        int firstResult = page > 0 ? page * Constants.Search.SEARCH_PER_PAGE : 0;
        nativeQuery.setFirstResult(firstResult);
        nativeQuery.setMaxResults(Constants.Search.SEARCH_PER_PAGE);
        result.setDatas(nativeQuery.getResultList());
        return result;
    }
}
