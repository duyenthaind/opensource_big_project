package com.group7.fruitswebsite.controller;

import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponse;
import com.group7.fruitswebsite.dto.transaction.MomoPaymentResponseData;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author duyenthai
 */
@Log4j
@Controller(value = "clientTransactionController")
@RequestMapping("/v1/api/transactions")
public class TransactionController {

    @PostMapping(value = "/momo_transaction", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
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

    @GetMapping("/momo_transaction")
    public String notifyTransaction() {
        return "client/index";
    }
}
