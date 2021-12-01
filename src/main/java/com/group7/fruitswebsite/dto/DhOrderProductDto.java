package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhOrderProductDto extends BaseDto{
    private Integer quantity;
    private Integer productId;
    private Integer order;
}
