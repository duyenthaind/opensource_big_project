package com.group7.fruitswebsite.dto.update;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
public abstract class UpdateCondition<T> {
    protected Map<String, Object> conditions;

    protected Map<String, Object> searches;

    protected UpdateCondition(T ent) {
    }

    protected void extractConditions(T ent){

    }

    protected void extractSearches(T ent){

    }
}
