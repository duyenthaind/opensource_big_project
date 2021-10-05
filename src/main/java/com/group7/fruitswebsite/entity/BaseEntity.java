package com.group7.fruitswebsite.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

	@Transient
	private Map<String, Object> map = new HashMap<>();

	@JsonAnyGetter
	public Map<String, Object> getMap() {
		return map;
	}

	@JsonAnySetter
	public void setMap(String key, Object value) {
		map.put(key, value);
	}

}
