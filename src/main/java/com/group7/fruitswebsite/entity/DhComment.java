package com.group7.fruitswebsite.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "dh_comment")
@Getter
@Setter
public class DhComment extends BaseEntity implements Serializable{
	
	@Column(name = "message",nullable = true)
	private String message;
	
	@Column(name = "parent_id",nullable = true)
	private Integer parent_id;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private DhProduct dhProduct;
	
	
}
