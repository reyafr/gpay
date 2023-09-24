package com.gpay.gpay.model.entity;

import com.gpay.gpay.common.CUSTTYPE;
import com.gpay.gpay.common.GENDER;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(schema = "payment", name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cust_id")
    private Integer custId;
    @Column(name = "cust_full_name")
    private String custFullName;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cust_type")
    private String custType;
    @Column(name = "refferal_code")
    private String refferalCode;
    @Column(name = "cust_pin_access")
    private String custPinAccess;
    @Column(name = "rekening_no")
    private String rekeningNo;
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "modified_by")
    private String modifiedBy;

    public Customer() {
    }

    public Customer(Integer custId, String custFullName, String email, String gender, String phoneNumber, String custType, String refferalCode, String custPinAccess, String rekeningNo, String status, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.custId = custId;
        this.custFullName = custFullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.custType = custType;
        this.refferalCode = refferalCode;
        this.custPinAccess = custPinAccess;
        this.rekeningNo = rekeningNo;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustFullName() {
        return custFullName;
    }

    public void setCustFullName(String custFullName) {
        this.custFullName = custFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getRefferalCode() {
        return refferalCode;
    }

    public void setRefferalCode(String refferalCode) {
        this.refferalCode = refferalCode;
    }

    public String getCustPinAccess() {
        return custPinAccess;
    }

    public void setCustPinAccess(String custPinAccess) {
        this.custPinAccess = custPinAccess;
    }

    public String getRekeningNo() {
        return rekeningNo;
    }

    public void setRekeningNo(String rekeningNo) {
        this.rekeningNo = rekeningNo;
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
