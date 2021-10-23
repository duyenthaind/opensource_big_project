package com.group7.fruitswebsite.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonProperty(value = "productName")
    private String productName;
    @JsonProperty(value = "detailDescription")
    private String detailDescription;
    @JsonProperty(value = "shortDescription")
    private String shortDescription;
    private Long price;
    @JsonProperty(value = "priceSale")
    private Long priceSale;
    @JsonProperty(value = "categoryId")
    private Integer categoryId;
    @JsonProperty(value = "image_path")
    private String imagePath;
    @JsonIgnore
    private MultipartFile[] files;
    @JsonProperty(value = "image_name")
    private String imageName;
    @JsonProperty(value = "productAvailable")
    @JsonAlias(value = "available")
    private long available = 0;
    @JsonProperty(value = "productWeight")
    @JsonAlias(value = "weight")
    private float weight = 0.0f;
    @JsonIgnore
    private List<String> pathUploadedAvatar;

}
