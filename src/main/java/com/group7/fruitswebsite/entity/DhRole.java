package com.group7.fruitswebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.catalina.User;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dh_role")
@Getter
@Setter
@ToString
public class DhRole extends BaseEntity implements java.io.Serializable{

	@Column(name = "name",nullable = false)
	private String name;

	@ManyToMany(mappedBy = "dhRoles")
	List<DhUser> dhUsers = new ArrayList<DhUser>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DhRole dhRole = (DhRole) o;
		return Objects.equals(name, dhRole.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}
}
