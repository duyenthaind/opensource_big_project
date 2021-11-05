package com.group7.fruitswebsite.dto.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class ProductCondition extends Condition {
    static {
        allowedConditions = new ArrayList<>(Arrays.asList("price", "name"));
    }
}
