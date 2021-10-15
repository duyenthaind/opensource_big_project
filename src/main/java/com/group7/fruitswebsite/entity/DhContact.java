package com.group7.fruitswebsite.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "dh_contact")
@Setter
@Getter
public class DhContact extends BaseEntity implements java.io.Serializable {

	@JsonProperty(value = "contact_email")
	@JsonAlias(value = "email")
	@Column(name = "email", nullable = false)
	private String email;

	@JsonProperty(value = "contact_name")
	@JsonAlias(value = "name")
	@Column(name = "name", nullable = false)
	private String name;

	@JsonProperty(value = "contact_message")
	@JsonAlias(value = "message")
	@Column(name = "message", nullable = false)
	private String message;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DhContact dhContact = (DhContact) o;
		return Objects.equals(email, dhContact.email) && Objects.equals(name, dhContact.name)
				&& Objects.equals(message, dhContact.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), email, name, message);
	}
}
