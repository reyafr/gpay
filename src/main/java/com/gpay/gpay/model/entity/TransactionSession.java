package com.gpay.gpay.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(schema = "payment", name = "transaction_session")
public class TransactionSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_session_id")
    private Integer transactionSessionId;
    @Column(name = "cust_id")
    private Integer custId;
    @Column(name = "key_id")
    private String keyId;
    @Column(name = "session_time")
    private Date sessionTime;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "status")
    private String status;

    public TransactionSession() {
    }

    public TransactionSession(Integer transactionSessionId, Integer custId, String keyId, Date sessionTime, String transactionType) {
        this.transactionSessionId = transactionSessionId;
        this.custId = custId;
        this.keyId = keyId;
        this.sessionTime = sessionTime;
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionSessionId() {
        return transactionSessionId;
    }

    public void setTransactionSessionId(Integer transactionSessionId) {
        this.transactionSessionId = transactionSessionId;
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

    public Date getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Date sessionTime) {
        this.sessionTime = sessionTime;
    }
}
