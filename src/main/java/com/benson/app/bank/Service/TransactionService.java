package com.benson.app.bank.Service;

import com.benson.app.bank.Dto.DepositDto;
import com.benson.app.bank.Dto.DepositRequest;
import com.benson.app.bank.Dto.WithdrawDto;
import com.benson.app.bank.Entity.BankAccount;
import com.benson.app.bank.Entity.Transaction;
import com.benson.app.bank.Repo.BankAccountRepository;
import com.benson.app.bank.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public DepositDto deposit(Long accountId, long amount) {
        Optional<BankAccount> accountOpt = bankAccountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            if (amount <= 0){
                return depositOutput(accountOpt.get(), amount, "Invalid amount");
            }
            BankAccount account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);

            Transaction transaction = new Transaction();
            transaction.setType("Deposit");
            transaction.setAmount(amount);
            transaction.setDateTime(LocalDateTime.now());
            transaction.setBankAccount(account);
            transactionRepository.save(transaction);
            bankAccountRepository.save(account);
            return depositOutput(accountOpt.get(), amount, "deposit successful");
        } else {
            return depositOutput(accountOpt.get(), amount, "Account not found");
        }
    }

    @Transactional
    public WithdrawDto withdraw(Long accountId, long amount) {
        Optional<BankAccount> accountOpt = bankAccountRepository.findById(accountId);
        if (accountOpt.isPresent()) {
            BankAccount account = accountOpt.get();
            if (account.getBalance() >= amount) {
                if (amount <= 0){
                    return withdrawalOutput(accountOpt.get(), amount, "Invalid amount");
                }
                account.setBalance(account.getBalance() - amount);

                Transaction transaction = new Transaction();
                transaction.setType("Withdraw");
                transaction.setAmount(amount);
                transaction.setDateTime(LocalDateTime.now());
                transaction.setBankAccount(account);
                transactionRepository.save(transaction);
                bankAccountRepository.save(account);
                return withdrawalOutput(accountOpt.get(), amount, "Withdraw successful");
            } else {
                return withdrawalOutput(accountOpt.get(), amount, "Insufficient funds");
            }
        } else {
            return withdrawalOutput(accountOpt.get(), amount, "Account not found");
        }
    }

    public DepositDto depositOutput(BankAccount bankAccount, long amount, String status){
        DepositDto depOutput = new DepositDto();
        depOutput.setStatus(status);
        depOutput.setAccountNumber(bankAccount.getAccountNumber());
        depOutput.setFullName(bankAccount.getFirstName() +
                " " + bankAccount.getMiddleName().charAt(0) +
                ". " + bankAccount.getLastName());
        depOutput.setDepositAmount(amount);
        depOutput.setBalance(bankAccount.getBalance());
        return depOutput;
    }

    public WithdrawDto withdrawalOutput(BankAccount bankAccount, long amount, String status) {
        WithdrawDto withdrawOutput = new WithdrawDto();
        withdrawOutput.setStatus(status);
        withdrawOutput.setAccountNumber(bankAccount.getAccountNumber());
        withdrawOutput.setFullName(bankAccount.getFirstName() + " " +
                bankAccount.getMiddleName().charAt(0) + ". " +
                bankAccount.getLastName());
        withdrawOutput.setWithdrawalAmount(amount);
        withdrawOutput.setBalance(bankAccount.getBalance());
        return withdrawOutput;
    }
}
