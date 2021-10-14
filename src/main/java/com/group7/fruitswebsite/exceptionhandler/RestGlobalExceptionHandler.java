package com.group7.fruitswebsite.exceptionhandler;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.JsonUtil;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author duyenthai
 */
@ControllerAdvice
@Log4j
public class RestGlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleMaxFileLimitException(HttpServletRequest request, Throwable throwable) {
        log.error("File uploaded exceeded limit, ", throwable);
        HttpStatus httpStatus = getStatus(request);
        JSONObject customData = new JSONObject();
        customData.put(Constants.CustomMessageField.MESSAGE, throwable.getCause());
        JSONObject jsonRes = JsonUtil.createResponseMessage(Constants.CustomMessage.FILE_EXCEED_LIMIT.getErrorCode(),
                Constants.CustomMessage.FILE_EXCEED_LIMIT.getMessage(), customData);
        return ApiResponseUtil.getCustomStatusWithMessage(jsonRes.toString(), httpStatus);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(statusCode);
    }

}
