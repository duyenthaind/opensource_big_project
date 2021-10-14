package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhContact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface ContactRepository extends JpaRepository<DhContact, Long> {
}
