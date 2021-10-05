package com.group7.fruitswebsite.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "dh_product_image")
public class DhProductImage extends BaseEntity implements java.io.Serializable{

	@Column(name = "path", nullable = true, length = 1000)
	private String path;

	@Column(name = "name", nullable = true, length = 50)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@JsonProperty(value = "product")
	private DhProduct dhProduct;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DhProductImage that = (DhProductImage) o;
		return Objects.equals(path, that.path) && Objects.equals(name, that.name) && Objects.equals(dhProduct, that.dhProduct);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), path, name, dhProduct);
	}
}
