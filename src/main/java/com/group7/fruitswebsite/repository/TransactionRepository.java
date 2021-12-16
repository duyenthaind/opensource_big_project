package com.group7.fruitswebsite.repository;

import com.group7.fruitswebsite.entity.DhTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface TransactionRepository extends JpaRepository<DhTransaction, Integer> {
    Optional<DhTransaction> findByTransactionIdAndRequestId(String transactionId, String requestId);

    Optional<DhTransaction> findByTransactionId(String transactionId);
}
