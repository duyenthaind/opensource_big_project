package com.group7.fruitswebsite.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dh_coupon")
@Getter
@Setter
public class DhCoupon extends BaseEntity implements Serializable {

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "total", nullable = false)
	private int total;

	@OneToMany(mappedBy = "dhCoupon", cascade = CascadeType.ALL)
	@JsonProperty(value = "order_products")
	private List<DhOrderProduct> orderProducts = new ArrayList<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DhCoupon other = (DhCoupon) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (total != other.total)
			return false;
		return true;
	}

}
