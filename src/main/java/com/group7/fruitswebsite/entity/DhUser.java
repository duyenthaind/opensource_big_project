package com.group7.fruitswebsite.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Table(name = "dh_user")
@Setter
@Getter
public class DhUser extends BaseEntity implements java.io.Serializable {

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false, length = 50)
	@JsonProperty(value = "username")
	private String username;

	@Column(name = "avatar", nullable = true, length = 500)
	@JsonProperty(value = "avatar")
	private String avatar;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "phone", nullable = true, columnDefinition = "TEXT")
	private String phone;

	@Column(name = "address", nullable = true, length = 14)
	private String address;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "dh_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@JsonProperty(value = "roles")
	private Set<DhRole> dhRoles = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "dh_user_product", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	@JsonProperty(value = "products")
	private Set<DhProduct> dhProducts = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dhUser")
	private List<DhOrder> orders = new ArrayList<>();
	
	public void likeProduct(DhProduct product) {
		dhProducts.add(product);
	}
	
	public void unlikeProduct(DhProduct product) {
		dhProducts.remove(product);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DhUser dhUser = (DhUser) o;
		return Objects.equals(email, dhUser.email) && Objects.equals(password, dhUser.password)
				&& Objects.equals(username, dhUser.username) && Objects.equals(dhRoles, dhUser.dhRoles)
				&& Objects.equals(orders, dhUser.orders)
				&& Objects.equals(avatar, dhUser.avatar)
				&& Objects.equals(dhProducts, dhUser.dhProducts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), email, password, username, dhRoles, orders,avatar,dhProducts);
	}
}
