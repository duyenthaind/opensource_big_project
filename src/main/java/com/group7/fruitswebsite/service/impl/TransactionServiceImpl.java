package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponse;
import com.group7.fruitswebsite.entity.DhTransaction;
import com.group7.fruitswebsite.entity.DhTransactionLog;
import com.group7.fruitswebsite.repository.OrderRepository;
import com.group7.fruitswebsite.repository.TransactionLogRepository;
import com.group7.fruitswebsite.repository.TransactionRepository;
import com.group7.fruitswebsite.service.TransactionService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionLogRepository transactionLogRepository;
    private OrderRepository orderRepository;

    @Override
    public void processMomoTransaction(MomoPaymentResponse momoPaymentResponse) {
        try {
            DhTransactionLog transactionLog = new DhTransactionLog();
            transactionLog.setTransactionId(momoPaymentResponse.getMomoTransId());
            transactionLog.setAmount(momoPaymentResponse.getAmount());
            transactionLog.setService(Constants.PaymentService.MOMO.getName());
            transactionLog.setResponseTime(momoPaymentResponse.getResponseTime());
            transactionLog.setCreatedDate(System.currentTimeMillis());
            transactionLogRepository.save(transactionLog);
            log.info(String.format("Save transaction log id %s, transaction id = %s", transactionLog.getId(), transactionLog.getTransactionId()));

            Optional<DhTransaction> optionalDhTransaction = transactionRepository.findByTransactionId(momoPaymentResponse.getMomoTransId());
            if (optionalDhTransaction.isPresent()) {
                DhTransaction currentTransaction = optionalDhTransaction.get();
                currentTransaction.setIsPaid(true);
                log.info(String.format("Update transaction %s", currentTransaction));
                transactionRepository.save(currentTransaction);

                if (Objects.nonNull(currentTransaction.getOrderId())) {
                    orderRepository.updateOrderFromTransactionId(currentTransaction.getOrderId());
                    log.info(String.format("Update order id %s status prepaid = true", currentTransaction.getOrderId()));
                }
            }
        } catch (Exception ex) {
            log.error("Process transaction error ", ex);
        }
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setTransactionLogRepository(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
