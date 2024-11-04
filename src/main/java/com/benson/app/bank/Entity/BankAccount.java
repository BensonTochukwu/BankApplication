package com.benson.app.bank.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 10, max = 10)
    private String accountNumber;

    private String accountType;

    @NotNull
    private long balance;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dob;
    private String address;
    private String bvn;
    private String accountStatus = "Active";

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_history")
    private List<Transaction> transactionHistory;


    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
