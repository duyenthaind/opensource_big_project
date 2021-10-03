package com.group7.fruitswebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "seo", nullable = false)
	private String seo;

	@Column(name = "parent_id", nullable = true)
	private Integer parentId;

	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", seo=" + seo + ", parentId=" + parentId
				+ "]";
	}

	
	
}
