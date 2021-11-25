package com.group7.fruitswebsite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author duyenthai
 */
@Entity
@Setter
@Getter
@Table(name = "dh_cart")
public class DhCart extends BaseEntity implements Serializable {
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "name")
	private String name;
	@Column(name = "avatar")
	private String avatar;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DhCart dhCart = (DhCart) o;
		return Objects.equals(productId, dhCart.productId) && Objects.equals(userId, dhCart.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), productId, userId);
	}
}
