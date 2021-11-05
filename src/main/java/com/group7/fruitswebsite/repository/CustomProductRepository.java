package com.group7.fruitswebsite.repository;

import java.util.List;

import com.group7.fruitswebsite.entity.DhProduct;

public interface CustomProductRepository {
	
	List<DhProduct> getTopRandom(int limit);
	
	List<DhProduct> getProductByListCategoryId(List<Integer> listId,int total);
	
	List<DhProduct> getProductsOrderByPriceSaleAsc();
}
