package com.group7.fruitswebsite.dto.transaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author duyenthai
 */
@Setter
@Getter
public class MomoPaymentResponse extends TransactionResponse {
    private String partnerCode;
    private String accessKey;
    private Long amount;
    private String partnerRefId;
    private String partnerTransId;
    private String transType;
    private String momoTransId;
    private Long responseTime;
    private String storeId;
}
