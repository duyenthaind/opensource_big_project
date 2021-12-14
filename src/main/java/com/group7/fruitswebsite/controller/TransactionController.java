package com.group7.fruitswebsite.controller;

import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponse;
import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponseData;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duyenthai
 */
@Log4j
@RestController(value = "clientTransactionController")
@RequestMapping("/v1/api/transactions")
public class TransactionController {

    @PostMapping("/momo_transaction")
    public ResponseEntity<MomoPaymentResponseData> processTransaction(@RequestBody MomoPaymentResponse momoPaymentResponse) {
        log.info(momoPaymentResponse);
        MomoPaymentResponseData momoPaymentResponseData = new MomoPaymentResponseData.Builder()
                .withStatus(0)
                .withMessage("Thành công")
                .withAmount(momoPaymentResponse.getAmount())
                .withPartnerRefId(momoPaymentResponse.getPartnerRefId())
                .withMomoTransId(momoPaymentResponse.getMomoTransId())
                .build();
        return ResponseEntity.ok(momoPaymentResponseData);
    }
}
