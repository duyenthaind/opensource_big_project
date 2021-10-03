package com.group7.fruitswebsite.entity;

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

import lombok.*;

@Entity
@Data
@Table(name = "dh_product")
public class DhProduct extends BaseEntity implements java.io.Serializable{
	
	@Column(name = "name",length = 1000,nullable = false)
	private String name;
	
	@Column(name = "detail_description",length = 1000,nullable = false)
	private String detailDescription;
	
	@Column(name = "short_description",length = 1000,nullable = false)
	private String shortDescription;
	
	@Column(name = "price",length = 1000,nullable = false)
	private Long price;
	
	@Column(name = "price_sale",length = 1000,nullable = false)
	private Long priceSale;
	
	@Column(name = "seo",length = 1000,nullable = false)
	private String seo;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private DhCategory category;
	
	@OneToMany(mappedBy = "dhProduct",cascade = CascadeType.ALL)
	private List<DhOrderProduct> orderProducts = new ArrayList<DhOrderProduct>();
	
	public void addOrderProduct(DhOrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
		orderProduct.setDhProduct(this);
	}
	
	public void removeOrderProduct(DhOrderProduct orderProduct) {
		this.orderProducts.remove(orderProduct);
		orderProduct.setDhProduct(null);
	}
	
	
}
