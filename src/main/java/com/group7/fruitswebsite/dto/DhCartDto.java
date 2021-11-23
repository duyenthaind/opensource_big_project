package com.group7.fruitswebsite.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class DhCartDto extends BaseDto{
    private Integer productId;
    private Integer quantity;
}
