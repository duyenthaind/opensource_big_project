package com.group7.fruitswebsite.common;

/**
 * @author duyenthai
 */
public class Constants {
    public enum RoleName {

        ADMIN("ADMIN", 1),
        USER("CLIENT", 2),
        SUPER_ADMIN("SUPER_ADMIN", 3);

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
        public static final String USERNAME_IS_ALREADY_DEFINED = "username is already defined";
        public static final String IDENTITY_IS_NOT_DEFINED = "identity information is not defined";
        public static final String CANNOT_SET_ROLE_TO_USER = "cannot define the role for this user";
        public static final String HAS_NO_AUTHORIZE_TO_CHANGE_OTHER_INFORMATION = "this user has no right to change other's information";
        public static final String NEW_ROLE_IS_NOT_SET = "new role is not set yet";
        public static final String NO_CHANGE = "no change to be made";
        public static final String USER_IS_SUPER = "super user cannot be changed role";
        public static final String ROLE_IS_NOT_SUPPORTED = "role is not supported";
        public static final String HAS_NO_AUTHORITIES_TO_CHANGE_TO_SUPER = "cannot change user role to super admin";
        public static final String CART_IS_NOT_FOUND = "cannot change cart because it is not found";
        public static final String NO_CART = "no cart";
        public static final String PRODUCT_ID_MUST_BE_NON_NULL = "you must provide product_id to add cart";
        public static final String CART_QUANTITY_MUST_BE_NON_NULL = "you must provide a valid quantity to update cart";
        public static final String ACCOUNT_IS_NOT_FOUND = "we cannot find you account and data with it";
    }

    public enum CustomMessage {
        FILE_EXCEED_LIMIT(1, "image exceeded limit"),
        USER_NOT_FOUND(2, "user is not found");

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

        public static class Product {
            private Product() {
            }

            public static final String NAME = "name";
            public static final String CATEGORY_ID = "category_id";
        }

        public static class Blog {
            private Blog() {
            }

            public static final int NUM_BLOGS_AT_SIDE_BAR = 3;
            public static final int BLOGS_PER_PAGE = 6;
            public static final String THUMBNAIL = "thumbnail";
        }

        public static final int SEARCH_PER_PAGE = 12;
    }

    public static class SessionItem {
        private SessionItem() {
        }

        public static final String CART = "cart";
    }

    public enum OrderStatus {
        UNAPPROVED(1, "Not approved yet"),
        APPROVED(2, "Approved"),
        GETTING_GOODS(3, "Getting item"),
        DELIVERING(4, "Item is being delivered"),
        COMPLETED(5, "Order is completed");

        private final int status;
        private final String description;

        OrderStatus(int status, String description) {
            this.status = status;
            this.description = description;
        }

        public int getStatus() {
            return status;
        }

        public String getDescription() {
            return description;
        }
    }
}
