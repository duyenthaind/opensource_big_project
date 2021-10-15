package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhContact;
import com.group7.fruitswebsite.model.DhContactModel;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface ContactService {
    ResponseEntity<ApiResponse> saveOne(DhContact dhContact);

    DhContact saveContact(DhContact dhContact);

    ResponseEntity<ApiResponse> saveOne(DhContactModel dhContactModel);
}
