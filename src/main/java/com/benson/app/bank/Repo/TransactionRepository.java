package com.benson.app.bank.Repo;

import com.benson.app.bank.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import com.benson.app.bank.Entity.Transaction;

import java.util.List;
import java.util.Objects;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByBankAccount(BankAccount account);
}
