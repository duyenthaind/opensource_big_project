package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DhCategoryModel extends BaseModel {
	private String name;
	private String description;
	private MultipartFile[] file;
	private String avatarName;
	private String currentPageCate;
	@JsonIgnore
	private String pathUploadedAvatar;
}
