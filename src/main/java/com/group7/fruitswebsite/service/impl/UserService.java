package com.group7.fruitswebsite.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService implements com.group7.fruitswebsite.service.UserService{

	@PersistenceContext
	protected EntityManager entityManager;
	
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public DhUser loadUserByUsername(String userName) {
		String jpql = "Select u From Users u Where u.username='" + userName + "'";
		try {		
			Query query = entityManager.createQuery(jpql, DhUser.class);
			return (DhUser) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error load user name");
			return null;
		}
	}
	
	@Override
	public DhUser findByUserName(String userName) {
		try {
			String jpql = "Select u From Users u Where u.username='" + userName + "'";
			Query query = entityManager.createQuery(jpql, DhUser.class);
			return (DhUser) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
