package com.group7.fruitswebsite.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class ApiResponse {
    @Setter
    @Getter
    private int status;
    @Setter
    @Getter
    private String datetime;
    @Setter
    @Getter
    private String message;
    @Setter
    @Getter
    private Object result;

    public ApiResponse(int status, String datetime, String message, Object result) {
        this.status = status;
        this.datetime = datetime;
        this.message = message;
        this.result = result;
    }

    public ApiResponse() {
    }

    public ApiResponse(Builder builder) {
        this.status = builder.status;
        this.datetime = builder.datetime;
        this.message = builder.message;
        this.result = builder.result;
    }

    public static class Builder {
        private int status;
        private String datetime;
        private String message;
        private Object result;

        private Builder() {
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withDateTime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withResult(Object object) {
            this.result = object;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }
}
