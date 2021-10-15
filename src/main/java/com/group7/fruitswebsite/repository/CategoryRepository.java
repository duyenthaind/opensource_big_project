package com.group7.fruitswebsite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group7.fruitswebsite.entity.DhCategory;

@Repository
public interface CategoryRepository extends JpaRepository<DhCategory, Integer>{

	Page<DhCategory> findAll(Pageable pageable);
	Page<DhCategory> findByName(String name,Pageable pageable);
	
}
