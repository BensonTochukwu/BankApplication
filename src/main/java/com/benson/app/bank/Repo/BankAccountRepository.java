package com.benson.app.bank.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.benson.app.bank.Entity.BankAccount;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    boolean existsByAccountNumber(String accountNumber);

    List<BankAccount> findAllByAccountType(String accountType);
}

