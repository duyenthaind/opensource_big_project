package com.group7.fruitswebsite.dto.search.condition;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class BlogCondition extends Condition{
    static {
        allowedConditions = new ArrayList<>(Arrays.asList("thumbnail", "created_date"));
    }
}
