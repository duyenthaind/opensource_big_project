package com.group7.fruitswebsite.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.repository.CustomProductRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Transactional
public class CustomProductRepositoryImpl implements CustomProductRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DhProduct> getTopRandom(int limit) {
		// TODO Auto-generated method stub
		String jpql = "SELECT p FROM DhProduct p order by RAND()";
		List<DhProduct> dhProducts = null;
		try {
			dhProducts = entityManager.createQuery(jpql, DhProduct.class).setMaxResults(limit).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error get top 9 random", e.getMessage());
		}

		return dhProducts;
	}

	@Override
	public List<DhProduct> getProductByListCategoryId(List<Integer> listId,int total) {
		String jpql = "select r.* from ( select r.*, ROW_NUMBER() over(PARTITION BY r.category_id) "
				+ "rn from dh_product r ) r where r.category_id in (:inIdList) and r.rn <= :total";
		
		List<DhProduct> dhProducts = null;
		Query query = entityManager.createNativeQuery(jpql, DhProduct.class);
		query.setParameter("inIdList", listId);
		query.setParameter("total", total);
		try {
			dhProducts = query.setMaxResults(16).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error get product by list category id", e.getMessage());
		}

		return dhProducts;
	}

}
