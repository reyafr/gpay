package com.gpay.gpay.repository;

import com.gpay.gpay.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    public int countFindByKeyId(String keyId );
    public int countFindByKeyIdRefund(String keyId );
    public Optional<Transaction> findByTransferOrderIdAndTransactionType(String transferOrderId ,String transactionType);
}
