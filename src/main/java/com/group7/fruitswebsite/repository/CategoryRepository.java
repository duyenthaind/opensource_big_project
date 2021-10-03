package com.group7.fruitswebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group7.fruitswebsite.entity.DhCategory;

@Repository
public interface CategoryRepository extends JpaRepository<DhCategory, Integer>{

}
