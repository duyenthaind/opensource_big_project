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
public class DhOrderModelUpdate extends BaseModel{
    private Integer orderId;
    private Boolean isPrepaid;
    private Integer orderStatus;
}
