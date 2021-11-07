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
    protected List<Integer> listPages = new ArrayList<Integer>();
   
    public void setTotalPages(int size) {
    	this.totalPages = this.total % size == 0 ? this.total / size : this.total / size + 1;
    }
    
    public void setListPages() {
    	if (this.totalPages >= 2) {
            for (int i = 0; i < this.totalPages; i++) {
            	this.listPages.add(i);
            }
        }
    }
}
