package com.group7.fruitswebsite.util;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author duyenthai
 */
@Log4j
public class PagingUtil {
    private PagingUtil() {
    }

    public static List<Integer> generateListPagingFromDataAndSize(List<Object> data, int size) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        if (size <= 0) {
            log.error(String.format("WTF is this size to generate paging, data instance %s, size %s", data.getClass(), size));
            return Collections.emptyList();
        }
        int total = data.size();
        List<Integer> result = new ArrayList<>();
        int index = -1;
        while (total > size * ++index) {
            result.add(index);
        }
        return result;
    }

    public static List<Integer> generateListPagingFromDataAndSize(int total, int size) {
        if (total <= 0) {
            log.error(String.format("WTF is this size to generate paging, data total %s, size %s", total, size));
            return Collections.emptyList();
        }
        if (size <= 0) {
            log.error(String.format("WTF is this size to generate paging, data total %s, size %s", total, size));
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int index = -1;
        while (total > size * ++index) {
            result.add(index);
        }
        return result;
    }
}
