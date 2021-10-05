package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class DhProductModel extends BaseModel {
    @JsonProperty(value = "product_name")
    private String productName;
    @JsonProperty(value = "detail_description")
    private String detailDescription;
    @JsonProperty(value = "short_description")
    private String shortDescription;
    private Long price;
    @JsonProperty(value = "price_sale")
    private Long priceSale;
    @JsonProperty(value = "category_id")
    private Integer categoryId;
    @JsonProperty(value = "image_path")
    private String imagePath;
    @JsonProperty(value = "image_name")
    private String imageName;

}
