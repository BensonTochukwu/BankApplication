package com.benson.app.bank.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WithdrawDto {
    private String status;
    private String accountNumber;
    private String fullName;
    private long withdrawalAmount;
    private long balance;
}
