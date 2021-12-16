package com.group7.fruitswebsite.dto;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.exceptions.UnknownPaymentMethodException;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author duyenthai
 */
@Getter
@Log4j
public class CheckoutResponse {
    private final Integer r;
    private final String msg;
    private final Integer type;
    private final String payUrl;
    private final String date;

    public CheckoutResponse(Builder builder) {
        this.r = builder.r;
        this.msg = builder.msg;
        this.type = builder.type;
        this.payUrl = builder.payUrl;
        date = (new Date()).toString();
    }

    public static class Builder {
        private Integer r;
        private String msg;
        private Integer type;
        private String payUrl;

        public Builder withRes(Integer r) {
            this.r = r;
            return this;
        }

        public Builder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder withType(Integer type) throws UnknownPaymentMethodException {
            Constants.PaymentMethod paymentMethod = Constants.PaymentMethod.getFromEnum(type);
            if (Objects.isNull(paymentMethod)) {
                log.error("Payment method is not supported");
                throw new UnknownPaymentMethodException("Payment method is not supported");
            }
            this.type = type;
            return this;
        }

        public Builder withPayUrl(String payUrl) {
            this.payUrl = payUrl;
            return this;
        }

        public CheckoutResponse build() {
            return new CheckoutResponse(this);
        }
    }

    public static CheckoutResponse getSuccessMomoResponseWithPayUrl(String payUrl) {
        try {
            return new CheckoutResponse.Builder()
                    .withRes(0)
                    .withMsg("ok")
                    .withType(Constants.PaymentMethod.MOMO.getType())
                    .withPayUrl(payUrl).build();
        } catch (Exception ex) {
            log.error("Error get sample momo response", ex);
        }
        return null;
    }

    public static CheckoutResponse getSuccessCodResponse() {
        try {
            return new CheckoutResponse.Builder()
                    .withRes(0)
                    .withMsg("ok")
                    .withType(Constants.PaymentMethod.COD.getType())
                    .withPayUrl(StringUtils.EMPTY)
                    .build();
        } catch (Exception ex) {
            log.error("Error get sample cod response", ex);
        }
        return null;
    }
}
