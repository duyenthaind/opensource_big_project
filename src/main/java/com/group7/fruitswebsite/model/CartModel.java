package com.group7.fruitswebsite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Getter
@Setter
@ToString
public class CartModel extends BaseModel {
    private Integer productId;
    private Integer quantity;
    private Integer userId;
}
