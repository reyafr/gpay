package com.gpay.gpay.model.dto;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class TransactionRequestDTO {
    private Integer custId;
    @NotEmpty
    private String keyId ;
    private Integer beneficiaryCustId;
    private String transactionDesc;
    private String idDevice;
    private BigDecimal amount;
    private Integer walletId ;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Integer getBeneficiaryCustId() {
        return beneficiaryCustId;
    }

    public void setBeneficiaryCustId(Integer beneficiaryCustId) {
        this.beneficiaryCustId = beneficiaryCustId;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }
}
