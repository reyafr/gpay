package com.gpay.gpay.controller;

import com.gpay.gpay.common.GenericResponseDTO;
import com.gpay.gpay.common.TRANSACTIONTYPE;
import com.gpay.gpay.exception.ResponseException;
import com.gpay.gpay.model.dto.RefundRequestDTO;
import com.gpay.gpay.model.dto.TopUpRequestDTO;
import com.gpay.gpay.model.dto.TransactionRequestDTO;
import com.gpay.gpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/gpay")
public class TransactionController {

    @Autowired
    private TransactionService paymentService;

    @PostMapping("/getSessionKey")
    public ResponseEntity<GenericResponseDTO<String>> getSessionKey(@RequestParam Integer custId, @RequestParam TRANSACTIONTYPE trxType) throws ResponseException {
        return new ResponseEntity<>(paymentService.getSessionKey(custId, trxType), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<GenericResponseDTO<String>> transactionWallet(@RequestBody TransactionRequestDTO request){
        return new ResponseEntity<>(paymentService.transfer(request), HttpStatus.OK);
    }

    @PostMapping("/topup")
    public ResponseEntity<GenericResponseDTO<String>> transactionTopUp(@RequestBody TopUpRequestDTO request){
        return new ResponseEntity<>(paymentService.topUp(request), HttpStatus.OK);
    }

    @PostMapping("/refund")
    public ResponseEntity<GenericResponseDTO<String>> transactionRefund(@RequestBody RefundRequestDTO request){
        return new ResponseEntity<>(paymentService.refund(request), HttpStatus.OK);
    }
}
