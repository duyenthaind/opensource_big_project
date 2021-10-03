package com.group7.fruitswebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Contact extends BaseModel{
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "message",nullable = false)
	private String message;
	
}
