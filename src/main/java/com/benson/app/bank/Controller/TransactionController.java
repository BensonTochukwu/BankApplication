package com.benson.app.bank.Controller;

import com.benson.app.bank.Dto.DepositDto;
import com.benson.app.bank.Dto.DepositRequest;
import com.benson.app.bank.Dto.WithdrawDto;
import com.benson.app.bank.Dto.WithdrawalRequest;
import com.benson.app.bank.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<DepositDto> deposit(@PathVariable Long accountId, @RequestBody DepositRequest request) {
        DepositDto response = transactionService.deposit(accountId, request.getAmount());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<WithdrawDto> withdraw(@PathVariable Long accountId, @RequestBody WithdrawalRequest request) {
        WithdrawDto response = transactionService.withdraw(accountId, request.getAmount());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
