package com.group7.fruitswebsite.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dh_blog_image")
public class DhBlogImage extends BaseEntity implements Serializable{
	@Column(name = "path", nullable = true, length = 1000)
	private String path;

	@Column(name = "name", nullable = true, length = 50)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	@JsonProperty(value = "blog")
	private DhBlog dhBlog;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DhBlogImage that = (DhBlogImage) o;
		return Objects.equals(path, that.path) && Objects.equals(name, that.name) && Objects.equals(dhBlog, that.dhBlog);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), path, name, dhBlog);
	}
	
	
}
