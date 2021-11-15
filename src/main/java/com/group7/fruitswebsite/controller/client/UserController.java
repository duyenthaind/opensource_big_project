package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.DhUserModel;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.service.impl.img.ImageUserServiceImpl;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author duyenthai
 */
@Log4j
@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final ImageService<DhUser> imageService = new ImageUserServiceImpl();

    private UserService userService;

    @PostMapping("/perform_signup")
    public ResponseEntity<ApiResponse> performSignup(@ModelAttribute DhUserModel userModel) {
        log.info(userModel);
        String avatarPath = imageService.saveUploadFiles(userModel.getFiles());
        userModel.setAvatar(avatarPath);
        return userService.saveOne(userModel);
    }

    @PutMapping("/users")
    public ResponseEntity<ApiResponse> update(@ModelAttribute DhUserModel userModel) {
        log.info(userModel);
        if (userModel.getId() == null && StringUtils.isEmpty(userModel.getUsername())) {
            log.error(String.format("Drop all action with model %s because it has no identity information", userModel));
            return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.IDENTITY_IS_NOT_DEFINED, HttpStatus.FORBIDDEN);
        }
        MultipartFile[] files = userModel.getFiles();
        if (files != null && files.length > 0) {
            Optional<DhUser> optional = imageService.checkExists(files[0], userModel.getId());
            if (optional.isPresent()) {
                userModel.setAvatar(optional.get().getAvatar());
                files = new MultipartFile[1];
            }
            String imagePath = imageService.saveUploadFiles(files);
            if (StringUtils.isEmpty(imagePath)) {
                userModel.setAvatar(imagePath);
            }
        }
        return userService.update(userModel);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
