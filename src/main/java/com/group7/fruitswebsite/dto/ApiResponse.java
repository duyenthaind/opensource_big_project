package com.group7.fruitswebsite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private ApiResponseResult result;

    public ApiResponse(int status, String datetime, String message, ApiResponseResult result) {
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
        private ApiResponseResult result;

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

        public Builder withResult(ApiResponseResult object) {
            this.result = object;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }

    public static class ApiResponseResult {
        private int page;
        @JsonProperty(value = "per_page")
        private int perPage;
        private int total;
        @JsonProperty(value = "total_pages")
        private int totalPages;
        private List data;

        public ApiResponseResult() {
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List getData() {
            return data;
        }

        public void setData(List data) {
            this.data = data;
        }
    }
}
