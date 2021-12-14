package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhOrderDto extends BaseDto {
    @JsonProperty(value = "code_name")
    private String codeName;
    @JsonProperty(value = "customer_name")
    private String customerName;
    @JsonProperty(value = "customer_email")
    private String customerEmail;
    @JsonProperty(value = "customer_phone")
    private String customerPhone;
    private Long total;
    private Boolean isPrepaid;
    private String note;
    private Integer couponId;
    private Integer userId;
    private Integer orderStatus;
    private String date;

    private List<DhOrderProductDto> listProductDto = new ArrayList<>();
}
