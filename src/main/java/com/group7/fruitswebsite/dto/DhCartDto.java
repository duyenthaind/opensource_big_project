package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DhCartDto extends BaseDto{
    private Integer productId;
    private Integer quantity;
    private String avatar;
    private String name;
}
