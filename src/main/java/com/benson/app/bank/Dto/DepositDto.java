package com.benson.app.bank.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositDto {
    private String status;
    private String accountNumber;
    private String fullName;
    private long depositAmount;
    private long balance;
}
