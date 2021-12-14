package com.group7.fruitswebsite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author duyenthai
 */
@Entity
@Table(name = "dh_transaction")
@Setter
@Getter
public class DhTransaction extends BaseEntity implements Serializable {
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "request_id")
    private String requestId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "price")
    private Long price;
    @Column(name = "is_paid", columnDefinition = "tinyint")
    private Boolean isPaid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhTransaction that = (DhTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(requestId, that.requestId) && Objects.equals(orderId, that.orderId) && Objects.equals(price, that.price) && Objects.equals(isPaid, that.isPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transactionId, requestId, orderId, price, isPaid);
    }
}
