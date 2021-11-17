package com.group7.fruitswebsite.controller.admin;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.ChangeRoleModel;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.StringUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@Log4j
@RestController(value = "userControllerSuper")
@RequestMapping("/api-super/v1/users")
public class SuperUserController {

    private UserService userService;

    @PutMapping("/users")
    public ResponseEntity<ApiResponse> updateUserRole(@RequestBody ChangeRoleModel changeRoleModel) {
        if (changeRoleModel.getUserId() == null) {
            log.error("Drop all action for model because it has no identity information");
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.IDENTITY_IS_NOT_DEFINED, HttpStatus.FORBIDDEN);
        }
        if (StringUtil.isNullOrEmpty(changeRoleModel.getNewRole())) {
            log.error("Drop all action for model because it has no new role");
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NEW_ROLE_IS_NOT_SET, HttpStatus.FORBIDDEN);
        }
        if (changeRoleModel.getOldRole().equalsIgnoreCase(changeRoleModel.getNewRole())) {
            log.debug("Drop action because there is no change to be made");
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NO_CHANGE, HttpStatus.ACCEPTED);
        }
        return userService.changeRole(changeRoleModel);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
