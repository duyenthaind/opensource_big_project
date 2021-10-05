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
public class DhUser extends BaseEntity implements java.io.Serializable{

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "username", nullable = false, length = 50)
	@JsonProperty(value = "username")
	private String username;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "dh_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@JsonProperty(value = "roles")
	private Set<DhRole> dhRoles = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "dhUser")
	private List<DhOrder> orders = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DhUser dhUser = (DhUser) o;
		return Objects.equals(email, dhUser.email) && Objects.equals(password, dhUser.password) && Objects.equals(username, dhUser.username) && Objects.equals(dhRoles, dhUser.dhRoles) && Objects.equals(orders, dhUser.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), email, password, username, dhRoles, orders);
	}
}
