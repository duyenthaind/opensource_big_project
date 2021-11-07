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
        REQUEST_TIMEOUT("request_timeout", 408),
        INTERNAL_SERVER("internal_server", 500);

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

    public static class ApiMessage {
        private ApiMessage() {
        }

        public static final String NOT_FOUND_CATEGORY = "category is not found";
        public static final String AVATAR_DEFINED_BUT_NOT_FOUND = "image may not be uploaded correctly";
        public static final String DATA_IS_NOT_VALID = "data is not valid";
        public static final String PRODUCT_ID_IS_NOT_DEFINED = "product id is not defined";
        public static final String BLOG_ID_IS_NOT_DEFINED = "blog id is not defined";
        public static final String PRODUCT_IN_COMMENT_NOT_DEFINED = "no product for comment is defined";
    }

    public enum CustomMessage {
        FILE_EXCEED_LIMIT(1, "image exceeded limit");

        private final int errorCode;
        private final String message;

        CustomMessage(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class CustomMessageField {
        private CustomMessageField() {
        }

        public static final String ERROR_CODE = "errorCode";
        public static final String MESSAGE = "message";
        public static final String CUSTOM_DATA = "customData";
    }

    public static class SystemUser {
        private SystemUser() {
        }

        public static final int SYSTEM_USER_ID = -999;
    }

    public static class Search {
        private Search() {
        }

        public static class Product{
            private Product(){
            }

            public static final String NAME = "name";
            public static final String CATEGORY_ID = "category_id";
        }

        public static final int SEARCH_PER_PAGE = 12;
    }
}
