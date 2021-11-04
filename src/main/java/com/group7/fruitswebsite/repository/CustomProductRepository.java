package com.group7.fruitswebsite.repository;

import java.util.List;

import com.group7.fruitswebsite.entity.DhProduct;

public interface CustomProductRepository {
	
	List<DhProduct> getTop9Random();
	
	List<DhProduct> getProductByListCategoryId(List<Integer> listId,int total);
}
