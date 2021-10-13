package com.group7.fruitswebsite.common;

/**
 * @author duyenthai
 */
public class Constants {
    public enum RoleName {

        ADMIN("ADMIN", 1),
        USER("USER", 2);

        private final String name;
        private final int index;

        RoleName(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public enum APIResponseStatus {
        SUCCESS_200("success", 200),
        FAILURE("failure", 400),
        UN_AUTHORIZED("unauthorized", 401),
        PAYMENT_REQUIRE("payment_required", 402),
        FORBIDDEN("forbidden", 403),
        NOT_FOUND("not_found", 404),
        REQUEST_TIMEOUT("request_timeout", 408);

        private final String message;
        private final int status;

        APIResponseStatus(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }

    public static class ApiMessage{
        public static final String NOT_FOUND_CATEGORY = "category is not found";
        public static final String AVATAR_DEFINED_BUT_NOT_FOUND = "image may not be uploaded correctly";
    }
}
