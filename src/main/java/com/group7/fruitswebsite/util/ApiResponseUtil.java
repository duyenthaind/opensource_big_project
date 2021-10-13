package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public class ApiResponseUtil {
    private ApiResponseUtil() {

    }

    public static ResponseEntity<ApiResponse> get404Entity() {
        return ResponseEntity.status(Constants.APIResponseStatus.NOT_FOUND.getStatus()).body(null);
    }

    public static ResponseEntity<ApiResponse> get403Entity() {
        return ResponseEntity.status(Constants.APIResponseStatus.FORBIDDEN.getStatus()).body(null);
    }

    public static ResponseEntity<ApiResponse> getStatusNotFoundCategory() {
        ApiResponse response = new ApiResponse.Builder()
                .withStatus(Constants.APIResponseStatus.FORBIDDEN.getStatus())
                .withMessage(Constants.ApiMessage.NOT_FOUND_CATEGORY)
                .withDateTime(DateUtil.currentDate())
                .withResult(null)
                .build();
        return ResponseEntity.status(Constants.APIResponseStatus.FORBIDDEN.getStatus()).body(response);
    }

    public static ResponseEntity<ApiResponse> getCustomStatusWithMessage(String msg, HttpStatus status){
        ApiResponse response = new ApiResponse.Builder()
                .withStatus(status.value())
                .withMessage(msg)
                .withDateTime(DateUtil.currentDate())
                .withResult(null)
                .build();
        return ResponseEntity.status(status.value()).body(response);
    }
}
