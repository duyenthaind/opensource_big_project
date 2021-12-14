package com.group7.fruitswebsite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author duyenthai
 */
@Entity
@Table(name = "dh_transaction_log")
@Getter
@Setter
public class DhTransactionLog extends BaseEntity{
    private String service;
    private Long amount;
    private String transactionId;
    private Long responseTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhTransactionLog that = (DhTransactionLog) o;
        return Objects.equals(service, that.service) && Objects.equals(amount, that.amount) && Objects.equals(transactionId, that.transactionId) && Objects.equals(responseTime, that.responseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), service, amount, transactionId, responseTime);
    }
}
