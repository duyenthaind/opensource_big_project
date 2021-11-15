package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.dto.search.condition.Condition;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author duyenthai
 */
public class QueryGeneratorUtil {
    private QueryGeneratorUtil() {
    }

    public static String generateQuery(List conditions, String tableName) {
        StringBuilder queryBuilder = new StringBuilder(String.format("select * from %s", tableName));
        if (!conditions.isEmpty()) {
            queryBuilder.append(" where ");
        }
        conditions.forEach(val -> {
            if (val instanceof Condition) {
                Condition condition = (Condition) val;
                if (condition.isValidCondition()) {
                    queryBuilder.append(generateOperation(condition));
                    if (conditions.indexOf(val) + 1 < conditions.size()) {
                        queryBuilder.append(" and ");
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

    public static String generateQueryUpdate(Map<String, Object> conditions, Map<String, Object> searches, String tableName) {
        StringBuilder queryBuilder = new StringBuilder(String.format("update %s set ", tableName));
        if (conditions == null || searches == null || conditions.isEmpty() || searches.isEmpty()) {
            return StringUtils.EMPTY;
        }

        int index = -1;
        for (String key : conditions.keySet()) {
            queryBuilder.append(String.format(" %s = :%s ", key, key));
            if (++index < conditions.size() - 1) {
                queryBuilder.append(" , ");
            }
        }
        queryBuilder.append(" where ");
        index = -1;
        for (String key : searches.keySet()) {
            queryBuilder.append(String.format(" %s = :%s ", key, key));
            if (++index < conditions.size() - 1) {
                queryBuilder.append(" and ");
            }
        }
        return queryBuilder.toString();
    }
}
