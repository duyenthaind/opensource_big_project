package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.group7.fruitswebsite.entity.DhCategory;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duyenthai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class DhProductDto extends BaseDto {
    private String name;
    private String detailDescription;
    private String shortDescription;
    private Long price;
    private Long priceSale;
    private Long available;
    private Long salePercent;
    private Float weight;
    private Integer categoryId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DhCategory category;
    private List<String> productImages;
    private List<DhProductImageDto> listProductImages;
    private int totalPages;
    @JsonAnySetter
    private Map<String, Object> any = new HashMap<>();

    public void setSalePercent() {
        this.salePercent = (long) ((1 - ((float) this.priceSale / this.price)) * 100);
    }
}
