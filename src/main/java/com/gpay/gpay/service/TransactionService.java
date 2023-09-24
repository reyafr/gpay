package com.gpay.gpay.service;

import com.gpay.gpay.common.GenericResponseDTO;
import com.gpay.gpay.common.TRANSACTIONTYPE;
import com.gpay.gpay.exception.ResponseException;
import com.gpay.gpay.model.dto.RefundRequestDTO;
import com.gpay.gpay.model.dto.TopUpRequestDTO;
import com.gpay.gpay.model.dto.TransactionRequestDTO;
import com.gpay.gpay.model.entity.*;
import com.gpay.gpay.repository.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionSessionRepo transactionSessionRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionDetailRepo transactionDetailRepo;

    public GenericResponseDTO<String> getSessionKey(Integer custId , TRANSACTIONTYPE trxType) throws ResponseException{
        Optional<Customer>  customer =
                customerRepo.findById(custId);

        if(customer.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Customer Not Found");
        }
        Optional<TransactionSession> trxSession =
                transactionSessionRepo.findByCustIdAndTransactionTypeAndStatus(custId,trxType.name(),"Active");
        if(trxSession.isPresent()){
            trxSession.get().setStatus("Inactive");
            transactionSessionRepo.save(trxSession.get());
        }
        TransactionSession trxSessionNew = new TransactionSession();
        trxSessionNew.setCustId(custId);
        trxSessionNew.setKeyId(UUID.randomUUID().toString());
        trxSessionNew.setTransactionType(trxType.name());
        trxSessionNew.setSessionTime(new Date());
        trxSessionNew.setStatus("Active");
        transactionSessionRepo.save(trxSessionNew);

        Map<String, Object> map = new HashMap<>();
        map.put("keyId", trxSessionNew.getKeyId());
        return new GenericResponseDTO().successResponse(map);
    }

    public GenericResponseDTO<String> transfer(TransactionRequestDTO request){
        Customer customerData ;
        Customer customerDataTarget ;
        Wallet walletData;
        Wallet walletBeneficiaryData;
        Optional<Customer>  customer =
                customerRepo.findById(request.getCustId());
        if(Strings.isEmpty(request.getKeyId())){
            return new GenericResponseDTO().noDataFoundResponse("Key Id Not Valid Please Re-Session");
        }

        if(customer.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Customer Not Found");
        }else{
            customerData = customer.get();
        }

        Optional<Customer>  customerTarget =
                customerRepo.findById(request.getBeneficiaryCustId());
        if(customerTarget.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Customer Target Not Found");
        }else{
            customerDataTarget = customerTarget.get();
        }

        Optional<TransactionSession>  session =
                transactionSessionRepo.findByCustIdAndKeyIdAndTransactionTypeAndStatus(
                        request.getCustId(),request.getKeyId(),TRANSACTIONTYPE.TRANSFER.name(), "Active");
        if(session.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Session Key Not Found");
        }

        int trxCountCurrent = transactionRepo.countFindByKeyId(request.getKeyId());

        if(trxCountCurrent > 0){
            return new GenericResponseDTO().errorResponse(HttpStatus.CONFLICT.value(),"Duplicate Request");
        }

        Optional<Wallet> wallet = walletRepo.findById(request.getWalletId());

        if(wallet.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Wallet Not Found");
        }else{
            walletData = wallet.get();
        }
//        System.out.println("Compare " + walletData.getBalance().compareTo(request.getAmount()));
        if(walletData.getBalance().compareTo(request.getAmount()) < 0){
            return new GenericResponseDTO().noDataFoundResponse("Balance Wallet Not enough");
        }

        Optional<Wallet> walletBeneficiary = walletRepo.findByCustId(request.getBeneficiaryCustId());
        if(walletBeneficiary.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Balance Wallet Beneficiary Not Found");
        }

        // add amount wallet Beneficiary
        walletBeneficiaryData = walletBeneficiary.get();
        walletBeneficiaryData.setBalance(walletBeneficiaryData.getBalance().add(request.getAmount()));
        walletBeneficiaryData.setModifiedDate(new Date());
        walletBeneficiaryData.setModifiedBy(request.getCustId().toString());
        walletRepo.save(walletBeneficiaryData);

        // subtract amount wallet giver
        walletData.setBalance(walletData.getBalance().subtract(request.getAmount()));
        walletData.setModifiedDate(new Date());
        walletData.setModifiedBy(request.getCustId().toString());
        walletRepo.save(walletData);

        String transferOrderId = UUID.randomUUID().toString();
        // insert transaction giver
        Transaction trx = new Transaction();
        trx.setCustomer(customerData);
        trx.setStatus("Done");
        trx.setKeyId(request.getKeyId());
        trx.setTransferOrderId(transferOrderId);
        trx.setBeneficiaryCustId(request.getBeneficiaryCustId());
        trx.setIdDevice(request.getIdDevice());
        trx.setTransactionDate(new Date());
        trx.setTransactionDesc(request.getTransactionDesc());
        trx.setTransactionType(TRANSACTIONTYPE.TRANSFER.name());
        trx.setCreatedBy(request.getCustId().toString());
        trx.setCreatedDate(new Date());
        transactionRepo.save(trx);

        // insert transaction Beneficiary
        Transaction trxBeneficiary = new Transaction();
        trxBeneficiary.setCustomer(customerDataTarget);
        trxBeneficiary.setStatus("Done");
        trxBeneficiary.setKeyId(request.getKeyId());
        trxBeneficiary.setTransferOrderId(transferOrderId);
        trxBeneficiary.setIdDevice(request.getIdDevice());
        trxBeneficiary.setTransactionDate(new Date());
        trxBeneficiary.setTransactionDesc(request.getTransactionDesc());
        trxBeneficiary.setTransactionType(TRANSACTIONTYPE.RECEIPTFUND.name());
        trxBeneficiary.setCreatedBy(request.getCustId().toString());
        trxBeneficiary.setCreatedDate(new Date());
        transactionRepo.save(trxBeneficiary);

        // insert transaction detail giver
        TransactionDetail trxDetail = new TransactionDetail();
        trxDetail.setTransaction(trx);
        trxDetail.setWallet(walletData);
        trxDetail.setAmount(request.getAmount());
        trxDetail.setStatus("Done");
        trxDetail.setCreatedBy(request.getCustId().toString());
        trxDetail.setCreatedDate(new Date());
        transactionDetailRepo.save(trxDetail);

        // insert transaction detail Beneficiary
        TransactionDetail trxDetailBeneficiary = new TransactionDetail();
        trxDetailBeneficiary.setTransaction(trxBeneficiary);
        trxDetailBeneficiary.setWallet(walletBeneficiaryData);
        trxDetailBeneficiary.setAmount(request.getAmount());
        trxDetailBeneficiary.setStatus("Done");
        trxDetailBeneficiary.setCreatedBy(request.getCustId().toString());
        trxDetailBeneficiary.setCreatedDate(new Date());
        transactionDetailRepo.save(trxDetailBeneficiary);

        Map<String, Object> map = new HashMap<>();
        map.put("transactionId", trx.getTransactionId());
        map.put("transferOrderId", transferOrderId);
        return new GenericResponseDTO().successResponse(map);
    }

    public GenericResponseDTO<String> topUp(TopUpRequestDTO request){
        Customer customerData ;
        Optional<Customer>  customer =
                customerRepo.findById(request.getCustId());
        Wallet walletData;
        if(Strings.isEmpty(request.getKeyId())){
            return new GenericResponseDTO().noDataFoundResponse("Key Id Not Valid Please Re-Session");
        }
        if(customer.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Customer Not Found");
        }else{
            customerData = customer.get();
        }
        Optional<TransactionSession>  session =
                transactionSessionRepo.findByCustIdAndKeyIdAndTransactionTypeAndStatus(
                        request.getCustId(),request.getKeyId(),TRANSACTIONTYPE.TOP_UP.name(),"Active");
        if(session.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Session Key Not Found");
        }

        int trxCountCurrent = transactionRepo.countFindByKeyId(request.getKeyId());

        if(trxCountCurrent > 0){
            return new GenericResponseDTO().errorResponse(HttpStatus.CONFLICT.value(),"Duplicate Request");
        }

        Optional<Wallet> wallet = walletRepo.findById(request.getWalletId());

        if(wallet.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Wallet Not Found");
        }else{
            walletData = wallet.get();
        }

        // insert transaction giver
        Transaction trx = new Transaction();
        trx.setCustomer(customerData);
        trx.setStatus("Done");
        trx.setKeyId(request.getKeyId());
        trx.setIdDevice(request.getIdDevice());
        trx.setTransactionDate(new Date());
        trx.setTransactionType(TRANSACTIONTYPE.TOP_UP.name());
        trx.setCreatedBy(request.getCustId().toString());
        trx.setCreatedDate(new Date());
        transactionRepo.save(trx);

        // add amount wallet giver
        walletData.setBalance(walletData.getBalance().add(request.getAmount()));
        walletData.setModifiedDate(new Date());
        walletData.setModifiedBy(request.getCustId().toString());
        walletRepo.save(walletData);

        // insert transaction detail giver
        TransactionDetail trxDetail = new TransactionDetail();
        trxDetail.setTransaction(trx);
        trxDetail.setWallet(walletData);
        trxDetail.setAmount(request.getAmount());
        trxDetail.setPaymentMethod(request.getPaymentMethod().name());
        trxDetail.setStatus("Done");
        trxDetail.setCreatedBy(request.getCustId().toString());
        trxDetail.setCreatedDate(new Date());
        transactionDetailRepo.save(trxDetail);

        Map<String, Object> map = new HashMap<>();
        map.put("transactionId", trx.getTransactionId());
        return new GenericResponseDTO().successResponse(map);
    }

    public GenericResponseDTO<String> refund(RefundRequestDTO request){
        Transaction trxData ;
        BigDecimal amountTransfer ;
        String transferOrderId ;
        Integer walletIdSender ;
        Integer walletIdBeneficiary;
        if(Strings.isEmpty(request.getKeyId())){
            return new GenericResponseDTO().noDataFoundResponse("Key Id Not Valid Please Re-Sesson");
        }

        Optional<TransactionSession>  session =
                transactionSessionRepo.findByCustIdAndKeyIdAndTransactionTypeAndStatus(
                        request.getCustId(),request.getKeyId(),TRANSACTIONTYPE.REFUND.name(),"Active");
        if(session.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Session Key Not Found");
        }
        int trxKey =
                transactionRepo.countFindByKeyIdRefund(request.getKeyId());
        if(trxKey > 0){
            return new GenericResponseDTO().errorResponse(HttpStatus.CONFLICT.value(),"Duplicate Request");
        }

        // find transaction id
        Optional<Transaction> trx =
                transactionRepo.findById(request.getTransactionId());

        if(trx.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Not Found");
        }
        trxData = trx.get();
        transferOrderId = trxData.getTransferOrderId();
        trxData.setStatus("REFUND");
        trxData.setKeyIdRefund(request.getKeyId());
        transactionRepo.save(trxData);

        // refund amount sender
        Optional<TransactionDetail> trxDtlSender
                = transactionDetailRepo.findByTransactionId(request.getTransactionId());
        if(trxDtlSender.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Detail Sender Not Found");
        }

        amountTransfer = trxDtlSender.get().getAmount();
        walletIdSender = trxDtlSender.get().getWallet().getWallet_id();
        Optional<Wallet> walletSender = walletRepo.findById(walletIdSender);
        if(walletSender.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Detail Sender Not Found");
        }
        walletSender.get().setBalance(walletSender.get().getBalance().add(amountTransfer));
        walletRepo.save(walletSender.get());

        // substrct amount Beneficiary
        Optional<Transaction> trxBeneficiary
                =transactionRepo.findByTransferOrderIdAndTransactionType(transferOrderId,"RECEIPTFUND");

        if(trxBeneficiary.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Beneficiary Not Found");
        }
        trxBeneficiary.get().setStatus("REFUND");
        trxBeneficiary.get().setKeyIdRefund(request.getKeyId());
        transactionRepo.save(trxBeneficiary.get());

        Optional<TransactionDetail> trxDtlBeneficiary
                = transactionDetailRepo.findByTransactionId(trxBeneficiary.get().getTransactionId());
        if(trxDtlBeneficiary.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Detail Beneficiary Not Found");
        }
        walletIdBeneficiary = trxDtlBeneficiary.get().getWallet().getWallet_id();
        Optional<Wallet> walletBeneficiary = walletRepo.findById(walletIdBeneficiary);
        if(walletBeneficiary.isEmpty()){
            return new GenericResponseDTO().noDataFoundResponse("Transaction Detail Beneficiary Not Found");
        }
        walletBeneficiary.get().setBalance(walletBeneficiary.get().getBalance().subtract(amountTransfer));
        walletRepo.save(walletSender.get());

        Map<String, Object> map = new HashMap<>();
        map.put("transactionId", "Success");
        return new GenericResponseDTO().successResponse(map);
    }
}
