package com.group7.fruitswebsite.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author duyenthai
 */
@Setter
@Getter
@ToString
public class DhOrderModel extends BaseModel {
    @JsonProperty(value = "customer_name")
    @JsonAlias(value = "customerName")
    private String customerName;
    @JsonProperty(value = "customer_email")
    @JsonAlias(value = "customerEmail")
    private String customerEmail;
    @JsonProperty(value = "customer_phone")
    @JsonAlias(value = "customerPhone")
    private String customerPhone;
    @JsonProperty(value = "customer_address")
    @JsonAlias(value = "customerAddress")
    private String customerAddress;
    private String note;
    private String couponCode;
    private Integer paymentMethod;
    private String transactionId;
    private String requestId;
    private String payUrl;
}
