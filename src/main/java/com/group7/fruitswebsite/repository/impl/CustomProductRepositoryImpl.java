package com.group7.fruitswebsite.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.repository.CustomProductRepository;

@Transactional
public class CustomProductRepositoryImpl implements CustomProductRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DhProduct> getTop3Random() {
		// TODO Auto-generated method stub
		String jpql = "SELECT p FROM DhProduct p order by RAND()";
//		List<DhProduct> dhProducts = entityManager.createQuery(jpql,DhProduct.class).setMaxResults(3).get;
		return null;
	}
	
	
	
}
