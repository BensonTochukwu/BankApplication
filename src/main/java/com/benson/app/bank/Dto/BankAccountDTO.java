package com.benson.app.bank.Dto;


import com.benson.app.bank.Entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private long id;
    private String accountNumber;
    private String accountType;
    private long balance;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String bvn;
    private String accountStatus;
    private List<Transaction> transactionHistory;

}
