package com.group7.fruitswebsite.common.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author duyenthai
 */
public enum Operator {
    @JsonProperty(value = "equal")
    EQUAL(1),
    @JsonProperty(value = "not_equal")
    NOT_EQUAL(2),
    @JsonProperty(value = "greater_than")
    GREATER_THAN(3),
    @JsonProperty(value = "greater_than_or_equal")
    GREATER_THAN_OR_EQUAL(4),
    @JsonProperty(value = "less_than")
    LESS_THAN(5),
    @JsonProperty(value = "less_than_or_equal")
    LESS_THAN_OR_EQUAL(6),
    @JsonProperty(value = "like")
    LIKE(7);
    private final int operation;

    Operator(int operation) {
        this.operation = operation;
    }

    @JsonValue
    public int getOperation() {
        return operation;
    }
}
