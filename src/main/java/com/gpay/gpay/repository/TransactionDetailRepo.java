package com.gpay.gpay.repository;

import com.gpay.gpay.model.entity.TransactionDetail;
import com.gpay.gpay.model.entity.TransactionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionDetailRepo extends JpaRepository<TransactionDetail, Integer> {

    @Query("FROM TransactionDetail  WHERE transaction.transactionId=?1")
    public Optional<TransactionDetail> findByTransactionId(Integer transactionId);

}
