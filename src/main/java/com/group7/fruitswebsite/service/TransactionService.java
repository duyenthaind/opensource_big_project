package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponse;

/**
 * @author duyenthai
 */
public interface TransactionService {
    void processMomoTransaction(MomoPaymentResponse momoPaymentResponse);
}
