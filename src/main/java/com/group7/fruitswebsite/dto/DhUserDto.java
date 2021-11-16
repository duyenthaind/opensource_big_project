package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhUserDto extends BaseDto{
	private String email;
	private String username;
	private String avatar;
	private String name;
	private String phone;
	private String address;
}
