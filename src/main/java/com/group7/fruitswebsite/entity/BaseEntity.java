package com.group7.fruitswebsite.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "status",nullable = true)
	private Boolean status = Boolean.TRUE;

	@Column(name = "updated_by",nullable = true)
	private Integer updatedBy;
	
	@Column(name = "created_by",nullable = true)
	private Integer createdBy;

	@Column(name = "updated_date",nullable = true)
	private Date updatedDate;

	@Column(name = "created_date",nullable = true)
	private Date createdDate;

}
