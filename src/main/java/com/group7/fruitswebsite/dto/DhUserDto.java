package com.group7.fruitswebsite.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhUserDto extends BaseDto{
	private String email;
	private String password;
	private String username;
	private String avatar;
	private String name;
	private String phone;
	private String address;
	private Set<DhRole> dhRoles = new HashSet<>();
	private Set<DhProduct> dhProducts = new HashSet<>();
	private List<DhOrder> orders = new ArrayList<>();
}
