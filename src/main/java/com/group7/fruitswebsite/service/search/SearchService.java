package com.group7.fruitswebsite.service.search;

import java.util.List;

/**
 * @author duyenthai
 */
public interface SearchService<T,Y> {
    T search(List<Y> conditions, int page);
}
