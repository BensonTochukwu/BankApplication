package com.benson.app.bank.Service;

import com.benson.app.bank.Dto.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.benson.app.bank.Entity.BankAccount;
import com.benson.app.bank.Repo.BankAccountRepository;

import java.util.*;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createAccount(BankAccount bankAccount) {
        bankAccount.setAccountNumber(generateAccountNumber());
        bankAccount.setBalance(0L);
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccountDTO> getAllAccounts(String string) {
        if (null != string && !string.isEmpty()) {
            List<BankAccount> account = bankAccountRepository.findAllByAccountType(string);
            //todo retrun error if nothing is found
            return checkIsAccountEmpty(account);
        }
        List<BankAccount> account = bankAccountRepository.findAll();
        return checkIsAccountEmpty(account);
    }
    public BankAccount updateAccount(long id, BankAccountDTO bankAccountDTO) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setFirstName(bankAccountDTO.getFirstName());
            bankAccount.setMiddleName(bankAccountDTO.getMiddleName());
            bankAccount.setLastName(bankAccountDTO.getLastName());
            bankAccount.setAddress(bankAccountDTO.getAddress());
            bankAccount.setAccountType(bankAccountDTO.getAccountType());
            bankAccount.setBvn(bankAccountDTO.getBvn());
            bankAccount.setDob(bankAccountDTO.getDob());

            bankAccountRepository.save(bankAccount);
            return bankAccount;
        }
        return null;
    }


    public String generateAccountNumber() {
        String accountNumber;
        Random random = new Random();
        do {
            accountNumber = String.format("%010d", random.nextInt(1_000_000_000));
        } while (bankAccountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    public BankAccountDTO fetchAccountDetails(BankAccount bankAccount) {
        BankAccountDTO respAcct = new BankAccountDTO();
        respAcct.setAccountNumber(bankAccount.getAccountNumber());
        respAcct.setFirstName(bankAccount.getFirstName());
        respAcct.setLastName(bankAccount.getLastName());
        respAcct.setMiddleName(bankAccount.getMiddleName());
        respAcct.setAccountType(bankAccount.getAccountType());
        respAcct.setBvn(bankAccount.getBvn());
        respAcct.setAccountStatus(bankAccount.getAccountStatus());
        respAcct.setBalance(bankAccount.getBalance());
        respAcct.setId(bankAccount.getId());
        respAcct.setDob(bankAccount.getDob());
        respAcct.setAddress(bankAccount.getAddress());
        respAcct.setTransactionHistory(bankAccount.getTransactionHistory());
        return respAcct;
    }

    private List<BankAccountDTO> checkIsAccountEmpty(List<BankAccount> account) {
        List<BankAccountDTO> respAccList = new ArrayList<>();
        if (!account.isEmpty()) {
            for (BankAccount bankAccount : account) {
                respAccList.add(fetchAccountDetails(bankAccount));
            }
            return respAccList;
        }
        else {
            errorIfAccountNotFound("No account available");
        }
        return respAccList;
    }

    private void errorIfAccountNotFound(String status){
    }
}