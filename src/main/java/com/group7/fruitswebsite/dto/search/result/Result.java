package com.group7.fruitswebsite.dto.search.result;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class Result<T> {
    protected int total;
    protected int totalPages;
    protected List<T> datas;
    protected List<Integer> listPages = new ArrayList<>();

    public void generateTotalPages(int size) {
        if (size <= 0) {
            this.totalPages = 0;
        } else {
            this.totalPages = this.total > 0 && this.total % size == 0 ? this.total / size : this.total / size + 1;
        }
    }

    public void generateListPages() {
        if (this.totalPages >= 1) {
            for (int i = 0; i < this.totalPages; i++) {
                this.listPages.add(i);
            }
        }
    }
}
