package com.gpay.gpay.repository;

import com.gpay.gpay.model.entity.Transaction;
import com.gpay.gpay.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {
    @Query("FROM Wallet  WHERE customer.custId=?1")
    public Optional<Wallet> findByCustId(Integer custId);
}
