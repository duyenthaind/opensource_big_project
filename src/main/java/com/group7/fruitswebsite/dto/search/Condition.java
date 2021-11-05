package com.group7.fruitswebsite.dto.search;

import com.group7.fruitswebsite.common.search.Operator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
@Setter
@Getter
public abstract class Condition {
    protected String key;
    protected Object value;
    protected Operator operator;
    protected static List<String> allowedConditions = new ArrayList<>();
    protected static List<String> alternativeConditions = new ArrayList<>();

    protected boolean isValidCondition() {
        return allowedConditions.contains(key) || alternativeConditions.contains(key);
    }

}
