package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhSubscriberModel;
import com.group7.fruitswebsite.service.SubscriberService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author duyenthai
 */
@Log4j
@RestController(value = "clientSubscriberController")
@RequestMapping("/v1/api/client-subscriber")
public class SubscriberController {
    private SubscriberService subscriberService;

    @PostMapping("/subscribers")
    public ResponseEntity<ApiResponse> saveOne(@RequestBody DhSubscriberModel dhSubscriberModel) {
        return subscriberService.saveOne(dhSubscriberModel);
    }

    @Autowired
    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }
}
