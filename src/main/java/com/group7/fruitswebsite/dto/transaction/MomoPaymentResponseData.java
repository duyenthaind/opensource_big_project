package com.group7.fruitswebsite.dto.transaction;

import com.group7.fruitswebsite.config.ApplicationConfig;
import lombok.Getter;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * @author duyenthai
 */
@Getter
public class MomoPaymentResponseData extends TransactionResponseData {
    private final Integer status;
    private final String message;
    private final Long amount;
    private final String partnerRefId;
    private final String momoTransId;
    private final String signature;

    private MomoPaymentResponseData(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.amount = builder.amount;
        this.partnerRefId = builder.partnerRefId;
        this.momoTransId = builder.momoTransId;
        this.signature = genSignature();
    }

    public static class Builder {
        private Integer status;
        private String message;
        private Long amount;
        private String partnerRefId;
        private String momoTransId;

        public Builder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder withPartnerRefId(String partnerRefId) {
            this.partnerRefId = partnerRefId;
            return this;
        }

        public Builder withMomoTransId(String momoTransId) {
            this.momoTransId = momoTransId;
            return this;
        }

        public MomoPaymentResponseData build() {
            return new MomoPaymentResponseData(this);
        }
    }

    protected String genSignature() {
        String rawHash = "amount=%s&message=%s&momoTransId=%s&partnerRefId=%s&status=%s";
        rawHash = String.format(rawHash, amount, message, momoTransId, partnerRefId, status);
        HmacUtils hm256 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, ApplicationConfig.MOMO_SECRET_KEY);
        return hm256.hmacHex(rawHash);
    }
}
