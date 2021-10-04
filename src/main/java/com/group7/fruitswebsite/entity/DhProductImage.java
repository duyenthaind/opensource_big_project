package com.group7.fruitswebsite.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Data
@Table(name = "dh_product_image")
public class DhProductImage extends BaseEntity implements java.io.Serializable{

	@Column(name = "path", nullable = true, length = 1000)
	private String path;

	@Column(name = "name", nullable = true, length = 50)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private DhProduct dhProduct;
}