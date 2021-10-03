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

    public enum APIResponseStatus{
        SUCCESS_200("success", 200),
        FAILURE("failure", 400)
        ;

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
}
