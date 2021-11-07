package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.dto.search.condition.Condition;
import com.group7.fruitswebsite.dto.search.condition.ProductSearchDto;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author duyenthai
 */
public class QueryGeneratorUtil {
    private QueryGeneratorUtil() {
    }

    public static String generateQuery(List conditions, String tableName) {
        StringBuilder queryBuilder = new StringBuilder("select * from ");
        queryBuilder.append(String.format("%s where ", tableName));
        conditions.forEach(val -> {
            if (val instanceof Condition) {
                Condition condition = (Condition) val;              
                if (condition.isValidCondition()) {
                    queryBuilder.append(generateOperation(condition));
                    if (conditions.indexOf(val) + 1 < conditions.size()) {
                        queryBuilder.append(" and ");
                    }
                    if(condition.getValue().getCategoryId() > 0 && condition.getValue().getCategoryId() != null) {
                    	queryBuilder.append(" and name like :name");
                    }
                }
            }
        });
        return queryBuilder.toString();
    }

    private static String generateOperation(Condition condition) {
        String operation;
        switch (condition.getOperator()) {
            case EQUAL:
                operation = " %s = :%s ";
                break;
            case NOT_EQUAL:
                operation = " %s != :%s ";
                break;
            case GREATER_THAN:
                operation = " %s > :%s ";
                break;
            case GREATER_THAN_OR_EQUAL:
                operation = " %s >= :%s ";
                break;
            case LESS_THAN:
                operation = " %s < :%s ";
                break;
            case LESS_THAN_OR_EQUAL:
                operation = " %s <= :%s ";
                break;
            case LIKE:
                operation = " %s like :%s ";
                break;
            default:
                operation = StringUtils.EMPTY;
                break;
        }
        if (!operation.equals(StringUtils.EMPTY)) {
            return String.format(operation, condition.getKey(), condition.getKey());
        }
        return operation;
    }
}
