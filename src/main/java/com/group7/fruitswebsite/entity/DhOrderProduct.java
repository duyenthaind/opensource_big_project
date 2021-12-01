package com.group7.fruitswebsite.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "dh_order_product")
@JsonIgnoreProperties({"order"})
public class DhOrderProduct extends BaseEntity implements java.io.Serializable {

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private DhOrder order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DhOrderProduct that = (DhOrderProduct) o;
        return quantity == that.quantity && Objects.equals(productId, that.productId) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, productId, order);
    }
}
