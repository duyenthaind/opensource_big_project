package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhUserAndRoleDto;
import com.group7.fruitswebsite.entity.DhRole;
import com.group7.fruitswebsite.dto.DhUserDto;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.ChangeRoleModel;
import com.group7.fruitswebsite.model.DhUserModel;
import com.group7.fruitswebsite.repository.RoleRepository;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.UserService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class UserServiceImpl implements UserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Override
    public Optional<DhUser> findByUserName(String userName) {
        try {
            return userRepository.findByUsername(userName);
        } catch (Exception ex) {
            log.error("Error when find user by username");
        }
        return Optional.empty();
    }

    @Override
    public DhUserDto getUserByUsernameAsDto(String username) {
        try {
            DhUserDto result = new DhUserDto();
            Optional<DhUser> dhUser = findByUserName(username);
            if(dhUser.isPresent()) {
            	result = DtoUtil.getDtoFromUserDetail(dhUser.get(), objectMapper);
            }
            return result;
        } catch (Exception ex) {
            log.error("Get user error, ", ex);
        }
        return null;
    }
    
    @Override
    public ResponseEntity<ApiResponse> getAllWithPage(int page, int size){
    	try {
			Pageable pageable = PageRequest.of(page, size);
			Page<DhUser> pageUsers = userRepository.findAll(pageable);
			List<DhUserAndRoleDto> dhUserAndRoleDtos = pageUsers.getContent().stream()
					.map(val -> DtoUtil.getDtoFromUserAndRole(val, objectMapper)).collect(Collectors.toList());
			ApiResponse.ApiResponseResult responseResult = new ApiResponse.ApiResponseResult();
            int totalProducts = userRepository.findAll().size();
            int totalPages = totalProducts % size == 0 ? totalProducts / size : totalProducts / size + 1;
            responseResult.setData(dhUserAndRoleDtos);
            responseResult.setPage(pageUsers.getNumber() + 1);
            responseResult.setPerPage(pageUsers.getNumberOfElements());
            responseResult.setTotalPages(totalPages);
            responseResult.setTotal(dhUserAndRoleDtos.size());
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponseUtil.getBaseFailureStatus();
		}
    }
    
    @Override
    public ResponseEntity<ApiResponse> changeRole(ChangeRoleModel changeRoleModel) {
        try {
            Optional<DhRole> optionalNewRole = roleRepository.findByName(changeRoleModel.getNewRole());
            if (!optionalNewRole.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.ROLE_IS_NOT_SUPPORTED, HttpStatus.FORBIDDEN);
            }
            DhRole newRole = optionalNewRole.get();
            Optional<DhUser> optional = userRepository.findById(changeRoleModel.getUserId());
            Optional<DhRole> roleSuperOptional = roleRepository.findByName(Constants.RoleName.SUPER_ADMIN.getName());
            DhRole roleSuperAdmin = roleSuperOptional.orElse(new DhRole());
            if (newRole.equals(roleSuperAdmin)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.HAS_NO_AUTHORITIES_TO_CHANGE_TO_SUPER, HttpStatus.FORBIDDEN);
            }
            if (!optional.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.CustomMessage.USER_NOT_FOUND.getMessage(), HttpStatus.FORBIDDEN);
            }
            DhUser dhUser = optional.get();
            if (dhUser.getDhRoles().contains(newRole)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NO_CHANGE, HttpStatus.ACCEPTED);
            }
            if (dhUser.getDhRoles().contains(roleSuperAdmin)) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.USER_IS_SUPER, HttpStatus.FORBIDDEN);
            }
            dhUser.getDhRoles().clear();
            dhUser.getDhRoles().add(newRole);
            dhUser.setUpdatedDate(System.currentTimeMillis());
            userRepository.save(dhUser);
            log.info(String.format("Update user %s with new role %s", dhUser.getId(), newRole.getName()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Error when update role for user with id=%s", changeRoleModel.getUserId()));
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhUserModel userModel) {
        try {
            Optional<DhUser> existedUser = findByUserName(userModel.getUsername());
            if (existedUser.isPresent()) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.USERNAME_IS_ALREADY_DEFINED, HttpStatus.BAD_REQUEST);
            }
            DhUser user = objectMapper.readValue(objectMapper.writeValueAsString(userModel), DhUser.class);
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
            user.setCreatedDate(System.currentTimeMillis());
            Optional<DhRole> optional = roleRepository.findByName(Constants.RoleName.USER.getName());
            if (optional.isPresent()) {
                user.getDhRoles().add(optional.get());
            } else {
                log.error(String.format("Cannot set role for user %s, please contact admin for help", userModel));
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.CANNOT_SET_ROLE_TO_USER, HttpStatus.FORBIDDEN);
            }
            userRepository.save(user);
            log.info(String.format("Save one new user to system id=%s", user.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Error save new user, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> update(DhUserModel userModel) {
        try {
            DhUser user = objectMapper.readValue(objectMapper.writeValueAsString(userModel), DhUser.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.customUpdate(user);
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Update user with username=%s error", userModel.getUsername()), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
