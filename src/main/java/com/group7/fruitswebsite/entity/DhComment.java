package com.group7.fruitswebsite.entity;

import java.io.Serializable;

import javax.persistence.*;

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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",referencedColumnName = "id")
	private DhProduct dhProduct;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private DhUser dhUser;

}
