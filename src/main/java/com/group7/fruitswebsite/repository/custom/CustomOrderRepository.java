package com.group7.fruitswebsite.repository.custom;

import java.util.List;

import com.group7.fruitswebsite.entity.DhOrder;

public interface CustomOrderRepository {
	List<DhOrder> findByOrderStatus(Integer orderStatus);
}
