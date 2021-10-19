package com.group7.fruitswebsite.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
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
    
    private MultipartFile[] files;
    @JsonProperty(value = "image_name")
    private String imageName;
    @JsonProperty(value = "product_available")
    private long available = 0;
    @JsonProperty(value = "product_weight")
    private float weight = 0.0f;
    @JsonIgnore
    private List<String> pathUploadedAvatar;

}
