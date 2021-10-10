package com.group7.fruitswebsite.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "dh_order")
public class DhOrder extends BaseEntity implements java.io.Serializable{

	@Column(name = "code_name",nullable = false,length = 50)
	@JsonProperty(value = "code_name")
	private String codeName;

	@Column(name = "customer_name",nullable = false,length = 50)
	@JsonProperty(value = "customer_name")
	private String customerName;

	@Column(name = "customer_email",nullable = false,length = 50)
	@JsonProperty(value = "customer_email")
	private String customerEmail;

	@Column(name = "customer_phone",nullable = false,length = 14)
	@JsonProperty(value = "customer_phone")
	private String customerPhone;

	@Column(name = "customer_address",nullable = false,length = 200)
	@JsonProperty(value = "customer_address")
	private String customerAddress;

	@Column(name = "seo",nullable = true,length = 300)
	private String seo;

	@Column(name = "total",precision = 13,scale = 2)
	private Long total;

	@Column(name = "is_prepaid", nullable = false, columnDefinition = "tinyint")
	private Boolean isPrepaid;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id")
	private DhCoupon dhCoupon;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = true)
	@JsonProperty(value = "user")
	private DhUser dhUser;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
	@JsonProperty(value = "order_products")
	private List<DhOrderProduct> orderProducts = new ArrayList<>();

	public void addOrderProduct(DhOrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
		orderProduct.setOrder(this);
	}

	public void removeOrderProduct(DhOrderProduct orderProduct) {
		this.orderProducts.remove(orderProduct);
		orderProduct.setOrder(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DhOrder dhOrder = (DhOrder) o;
		return Objects.equals(codeName, dhOrder.codeName) && Objects.equals(customerName, dhOrder.customerName) && Objects.equals(customerEmail, dhOrder.customerEmail) && Objects.equals(customerPhone, dhOrder.customerPhone) && Objects.equals(customerAddress, dhOrder.customerAddress) && Objects.equals(seo, dhOrder.seo) && Objects.equals(total, dhOrder.total) && Objects.equals(isPrepaid, dhOrder.isPrepaid) && Objects.equals(dhUser, dhOrder.dhUser) && Objects.equals(orderProducts, dhOrder.orderProducts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), codeName, customerName, customerEmail, customerPhone, customerAddress, seo, total, isPrepaid, dhUser, orderProducts);
	}
}
