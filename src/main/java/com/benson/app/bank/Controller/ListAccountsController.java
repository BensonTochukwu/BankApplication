package com.benson.app.bank.Controller;

import com.benson.app.bank.Dto.BankAccountDTO;
import com.benson.app.bank.Entity.BankAccount;
import com.benson.app.bank.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list")
public class ListAccountsController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping(value = {"/all", "/all/{accountType}"})
    public List<BankAccountDTO> listAccounts(@PathVariable(required = false, value = "accountType") String accountType) {
        return bankAccountService.getAllAccounts(accountType);
    }

}
