package com.group7.fruitswebsite.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group7.fruitswebsite.entity.DhRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhUserAndRoleDto extends BaseDto{
	private String email;
	private String username;
	private String avatar;
	private String name;
	private String phone;
	private String address;
	private String role;
}
