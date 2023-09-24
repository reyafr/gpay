package com.gpay.gpay.repository;

import com.gpay.gpay.model.entity.TransactionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionSessionRepo extends JpaRepository<TransactionSession, Integer> {

    @Query("FROM TransactionSession  WHERE custId=?1 and keyId=?2 and transactionType =?3 and status =?4")
    public Optional<TransactionSession> findByCustIdAndKeyIdAndTransactionTypeAndStatus(Integer custId , String keyId,String transactionType , String status);

    @Query("FROM TransactionSession  WHERE custId=?1 and transactionType =?2 and status =?3")
    public Optional<TransactionSession> findByCustIdAndTransactionTypeAndStatus(Integer custId ,String transactionType, String status);

}
