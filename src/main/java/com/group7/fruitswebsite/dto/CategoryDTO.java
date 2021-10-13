package com.group7.fruitswebsite.dto;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {
	private String name;
	private String description;
	private MultipartFile[] file;
	private String avatarName;
}
