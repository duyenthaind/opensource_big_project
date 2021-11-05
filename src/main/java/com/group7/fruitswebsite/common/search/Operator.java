package com.group7.fruitswebsite.common.search;

/**
 * @author duyenthai
 */
public enum Operator {
    EQUAL(1)
    ;
    private final int operation;

    Operator(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }
}
