package com.group7.fruitswebsite.service.search;

import com.group7.fruitswebsite.dto.search.Condition;

import java.util.List;

/**
 * @author duyenthai
 */
public interface SearchService<T,Y> {
    List<T> search(List<Y> conditions);
}
