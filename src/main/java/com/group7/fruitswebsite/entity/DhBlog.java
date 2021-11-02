package com.group7.fruitswebsite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dh_blog")
@Getter
@Setter
public class DhBlog extends BaseEntity implements Serializable{
	
	@Column(name ="thumbnail",nullable = false,length = 200)
	private String thumbnail;
	
	@Column(name = "details",nullable = false, columnDefinition = "LONGTEXT")
	private String details;
	
	@Column(name = "short_description", nullable = false, columnDefinition = "LONGTEXT")
	private String shortDescription;

	@Column(name = "avatar", columnDefinition = "TEXT")
	private String avatar;
	
}
