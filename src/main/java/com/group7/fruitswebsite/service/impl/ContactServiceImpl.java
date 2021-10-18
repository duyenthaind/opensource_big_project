package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.entity.DhContact;
import com.group7.fruitswebsite.model.DhContactModel;
import com.group7.fruitswebsite.repository.ContactRepository;
import com.group7.fruitswebsite.service.ContactService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DateUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author duyenthai
 */
@Log4j
@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhContact dhContact) {
        try {
            contactRepository.save(dhContact);
            ApiResponse response = new ApiResponse.Builder()
                    .withStatus(Constants.APIResponseStatus.SUCCESS_200.getStatus())
                    .withMessage(Constants.APIResponseStatus.SUCCESS_200.getMessage())
                    .withDateTime(DateUtil.currentDate())
                    .withResult(null)
                    .build();
            log.info(String.format("Save 1 new contact message, id=%d", dhContact.getId()));
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("Error insert new contact message, ", ex);
            ApiResponse response = new ApiResponse(Constants.APIResponseStatus.FAILURE.getStatus(), DateUtil.currentDate(),
                    Constants.APIResponseStatus.FAILURE.getMessage(), null);
            return ResponseEntity.status(Constants.APIResponseStatus.FAILURE.getStatus()).body(response);
        }
    }

    @Override
    @Transactional
    public DhContact saveContact(DhContact dhContact) {
        try {
            contactRepository.save(dhContact);
        } catch (Exception ex) {
            log.error("Save contact message error, ", ex);
        }
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhContactModel dhContactModel) {
        try {
            String json = objectMapper.writeValueAsString(dhContactModel);
            DhContact dhContact = objectMapper.readValue(json, DhContact.class);
            dhContact.setCreatedDate(System.currentTimeMillis());
            dhContact.setUpdatedDate(0L);
            return saveOne(dhContact);
        } catch (Exception ex) {
            log.error("Error save contact message, ", ex);
        }
        return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.DATA_IS_NOT_VALID, HttpStatus.NOT_ACCEPTABLE);
    }
}
