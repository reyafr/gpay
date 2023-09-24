package com.gpay.gpay.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(schema = "payment", name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "transaction_desc")
    private String transactionDesc;
    @Column(name = "id_device")
    private String idDevice;
    @Column(name = "beneficiary_cust_id")
    private Integer beneficiaryCustId;
    @Column(name = "transfer_order_id")
    private String transferOrderId;
    @Column(name = "status")
    private String status;
    @Column(name = "key_id")
    private String keyId;
    @Column(name = "key_id_refund")
    private String keyIdRefund;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "modified_by")
    private String modifiedBy;

    public Transaction() {
    }

    public String getKeyIdRefund() {
        return keyIdRefund;
    }

    public void setKeyIdRefund(String keyIdRefund) {
        this.keyIdRefund = keyIdRefund;
    }

    public Transaction(Integer transactionId, Customer customer, String transactionType, Date transactionDate, String transactionDesc, String idDevice, Integer beneficiaryCustId, String status, String keyId, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.transactionId = transactionId;
        this.customer = customer;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionDesc = transactionDesc;
        this.idDevice = idDevice;
        this.beneficiaryCustId = beneficiaryCustId;
        this.status = status;
        this.keyId = keyId;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public String getTransferOrderId() {
        return transferOrderId;
    }

    public void setTransferOrderId(String transferOrderId) {
        this.transferOrderId = transferOrderId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public Integer getBeneficiaryCustId() {
        return beneficiaryCustId;
    }

    public void setBeneficiaryCustId(Integer beneficiaryCustId) {
        this.beneficiaryCustId = beneficiaryCustId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
