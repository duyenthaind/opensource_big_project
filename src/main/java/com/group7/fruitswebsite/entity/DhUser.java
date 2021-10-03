package com.group7.fruitswebsite.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "dh_user")
@Data
public class DhUser extends BaseEntity implements java.io.Serializable{

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "dh_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<DhRole> dhRoles = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "dhUser")
	private List<DhOrder> orders = new ArrayList<>();
	
	
}
