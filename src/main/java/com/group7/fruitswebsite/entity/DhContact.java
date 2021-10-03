package com.group7.fruitswebsite.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "dh_contact")
public class DhContact extends BaseEntity implements java.io.Serializable{
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "message",nullable = false)
	private String message;
	
}
