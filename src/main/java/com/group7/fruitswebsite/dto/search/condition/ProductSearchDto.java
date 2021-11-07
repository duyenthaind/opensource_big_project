package com.group7.fruitswebsite.dto.search.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductSearchDto {
	private Integer categoryId;
	private Object searchText;
	public ProductSearchDto(Integer categoryId, Object searchText) {
		super();
		this.categoryId = categoryId;
		this.searchText = searchText;
	}
}
