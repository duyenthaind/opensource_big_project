package com.group7.fruitswebsite.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group7.fruitswebsite.common.search.Operator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
@Setter
@Getter
@ToString
public abstract class Condition {
    protected String key;
    protected Object value;
    @JsonProperty(value = "operator")
    protected Operator operator;
    protected static List<String> allowedConditions = new ArrayList<>();
    protected static List<String> alternativeConditions = new ArrayList<>();

    public boolean isValidCondition() {
        return allowedConditions.contains(key) || alternativeConditions.contains(key);
    }

}
