package com.group7.fruitswebsite.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Getter
@Setter
public class CartModel extends BaseModel {
    private Integer productId;
    private Integer quantity;
    private Integer userId;
}
