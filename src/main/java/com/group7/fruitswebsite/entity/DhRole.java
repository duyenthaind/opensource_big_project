package com.group7.fruitswebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "dh_role")
@Data
public class DhRole extends BaseEntity implements java.io.Serializable{

	@Column(name = "name",nullable = false)
	private String name;
	
}
