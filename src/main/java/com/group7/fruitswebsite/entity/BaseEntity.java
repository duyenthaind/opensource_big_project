package com.group7.fruitswebsite.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty(value = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "created_by",nullable = true)
	@JsonProperty(value = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date",nullable = true)
	@JsonProperty(value = "updated_date")
	private Long updatedDate;

	@Column(name = "created_date",nullable = true)
	@JsonProperty(value = "created_date")
	private Long createdDate;

}
