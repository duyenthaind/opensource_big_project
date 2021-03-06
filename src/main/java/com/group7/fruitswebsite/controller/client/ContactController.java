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
@RestController(value = "contactClientController")
@RequestMapping(value = "/v1/api/client-contact")
public class ContactController {
    private ContactService contactService;


    @PostMapping(value = "/contacts", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<ApiResponse> saveOne(@ModelAttribute DhContactModel contactModel) {
        return contactService.saveOne(contactModel);
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
