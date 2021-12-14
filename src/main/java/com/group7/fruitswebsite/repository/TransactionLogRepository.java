package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duyenthai
 */
public interface TransactionLogRepository extends JpaRepository<DhTransactionLog, Integer> {
}
