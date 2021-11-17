package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhUserDto;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.ChangeRoleModel;
import com.group7.fruitswebsite.model.DhUserModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author duyenthai
 */
public interface UserService {
    Optional<DhUser> findByUserName(String userName);

    ResponseEntity<ApiResponse> saveOne(DhUserModel userModel);

    ResponseEntity<ApiResponse> update(DhUserModel userModel);

    DhUserDto getUserByUsernameAsDto(String username);

    ResponseEntity<ApiResponse> changeRole(ChangeRoleModel changeRoleModel);
}
