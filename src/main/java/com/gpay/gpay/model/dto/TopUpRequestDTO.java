package com.gpay.gpay.model.dto;

import com.gpay.gpay.common.PAYMENTMETHOD;

import java.math.BigDecimal;

public class TopUpRequestDTO {
    private Integer custId;
    private String keyId ;
    private String idDevice;
    private BigDecimal amount;
    private Integer walletId ;
    private PAYMENTMETHOD paymentMethod;

    public PAYMENTMETHOD getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PAYMENTMETHOD paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

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
