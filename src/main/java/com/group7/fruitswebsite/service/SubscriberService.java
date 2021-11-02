package com.group7.fruitswebsite.service;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhSubscriberModel;
import org.springframework.http.ResponseEntity;

/**
 * @author duyenthai
 */
public interface SubscriberService {
    ResponseEntity<ApiResponse> saveOne(DhSubscriberModel dhSubscriberModel);
}
