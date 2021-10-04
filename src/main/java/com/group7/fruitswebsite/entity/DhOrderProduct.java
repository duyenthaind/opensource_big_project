package com.group7.fruitswebsite.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Data
@Table(name = "dh_order_product")
public class DhOrderProduct extends BaseEntity implements java.io.Serializable{

	@Column(name = "quantity",nullable = false)
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@JsonProperty(value = "product")
	private DhProduct dhProduct;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private DhOrder order;
}
