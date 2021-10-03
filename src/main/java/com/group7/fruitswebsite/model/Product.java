package com.group7.fruitswebsite.model;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "product")
public class Product extends BaseModel{
	
	@Column(name = "name",length = 1000,nullable = false)
	private String name;
	
	@Column(name = "detail_description",length = 1000,nullable = false)
	private String detailDescription;
	
	@Column(name = "short_description",length = 1000,nullable = false)
	private String shortDescription;
	
	@Column(name = "price",length = 1000,nullable = false)
	private BigDecimal price;
	
	@Column(name = "price_sale",length = 1000,nullable = false)
	private BigDecimal priceSale;
	
	@Column(name = "seo",length = 1000,nullable = false)
	private String seo;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
	public void addOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
		orderProduct.setProduct(this);
	}
	
	public void removeOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.remove(orderProduct);
		orderProduct.setProduct(null);
	}
	
	
}
