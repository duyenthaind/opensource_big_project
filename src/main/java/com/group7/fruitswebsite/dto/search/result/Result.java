package com.group7.fruitswebsite.dto.search.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class Result<T> {
    protected int total;
    protected List<T> datas;
}
