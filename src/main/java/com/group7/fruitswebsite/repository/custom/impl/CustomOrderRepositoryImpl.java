package com.group7.fruitswebsite.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.repository.custom.CustomOrderRepository;

@Transactional
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DhOrder> findByOrderStatus(Integer orderStatus){
		String sql = "select * from dh_order where order_status = :orderStatus";
		Query query = entityManager.createNativeQuery(sql, DhOrder.class);
		query.setParameter("orderStatus", orderStatus);
		return query.getResultList();
	}
}
