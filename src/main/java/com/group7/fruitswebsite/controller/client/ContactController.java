package com.group7.fruitswebsite.controller.client;

import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.model.DhContactModel;
import com.group7.fruitswebsite.service.ContactService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author duyenthai
 */
@Log4j
@RestController
@RequestMapping(value = "/api/v1")
public class ContactController {
    private ContactService contactService;


    @PostMapping(value = "/send_contact", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ApiResponse> saveOne(@ModelAttribute DhContactModel contactModel){
        log.info(contactModel);
        return contactService.saveOne(contactModel);
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
