package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhSubscriber;
import com.group7.fruitswebsite.model.DhSubscriberModel;
import com.group7.fruitswebsite.repository.SubscriberRepository;
import com.group7.fruitswebsite.service.SubscriberService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberRepository subscriberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhSubscriberModel dhSubscriberModel) {
        try {
            DhSubscriber subscriber = objectMapper.readValue(objectMapper.writeValueAsString(dhSubscriberModel), DhSubscriber.class);
            subscriber.setCreatedDate(System.currentTimeMillis());
            subscriber.setCreatedBy(Constants.SystemUser.SYSTEM_USER_ID);
            subscriberRepository.save(subscriber);
            log.info(String.format("Save 1 new subscriber, id=%s", subscriber.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Error save new subscriber", ex);
            return ApiResponseUtil.get403Entity();
        }
    }

    @Autowired
    public void setSubscriberRepository(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }
}
