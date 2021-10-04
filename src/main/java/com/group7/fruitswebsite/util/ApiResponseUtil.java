package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public class ApiResponseUtil {
    private ApiResponseUtil(){

    }

    public static ResponseEntity<ApiResponse> get404Entity(){
        return ResponseEntity.status(Constants.APIResponseStatus.NOT_FOUND.getStatus()).body(null);
    }
}
