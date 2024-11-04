package com.benson.app.bank.Controller;

import com.benson.app.bank.Dto.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.benson.app.bank.Entity.BankAccount;
import com.benson.app.bank.Service.BankAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("create")
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount) {
        BankAccount account =  bankAccountService.createAccount(bankAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping( value = "/update/{accountId}")
    public BankAccount updateAccount(@PathVariable Long accountId, @RequestBody BankAccountDTO update){
        return bankAccountService.updateAccount(accountId, update);
    }

}
































