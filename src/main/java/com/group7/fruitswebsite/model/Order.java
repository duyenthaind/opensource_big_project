package com.group7.fruitswebsite.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseModel{
	
	@Column(name = "code_name",nullable = false,length = 50)
	private String codeName;
	
	@Column(name = "customer_name",nullable = false,length = 50)
	private String customerName;
	
	@Column(name = "customer_email",nullable = false,length = 50)
	private String customerEmail;
	
	@Column(name = "customer_phone",nullable = false,length = 14)
	private String customerPhone;
	
	@Column(name = "customer_address",nullable = false,length = 200)
	private String customerAddress;
	
	@Column(name = "seo",nullable = true,length = 300)
	private String seo;
	
	@Column(name = "total",precision = 13,scale = 2)
	private BigDecimal total;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = true)
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
	public void addOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
		orderProduct.setOrder(this);
	}
	
	public void removeOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.remove(orderProduct);
		orderProduct.setOrder(null);
	}
	
}
