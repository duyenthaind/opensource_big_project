package com.group7.fruitswebsite.dto;

import lombok.Getter;

/**
 * @author duyenthai
 */
@Getter
public class MomoRequestData {
    private final String partnerCode;
    private final String accessKey;
    private final String requestId;
    private final String orderId;
    private final Long amount;
    private final String returnUrl;
    private final String notifyUrl;

    private MomoRequestData(Builder builder) {
        this.partnerCode = builder.partnerCode;
        this.accessKey = builder.accessKey;
        this.requestId = builder.requestId;
        this.orderId = builder.orderId;
        this.amount = builder.amount;
        this.returnUrl = builder.returnUrl;
        this.notifyUrl = builder.notifyUrl;
    }

    public static class Builder {
        private String partnerCode;
        private String accessKey;
        private String requestId;
        private String orderId;
        private Long amount;
        private String returnUrl;
        private String notifyUrl;

        public Builder withPartnerCode(String partnerCode) {
            this.partnerCode = partnerCode;
            return this;
        }

        public Builder withAccessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public Builder withRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder withReturnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public Builder withNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public MomoRequestData build() {
            return new MomoRequestData(this);
        }
    }
}
