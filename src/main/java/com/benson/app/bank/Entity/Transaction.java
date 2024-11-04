package com.benson.app.bank.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "trans_id")
    private long transactionId;

    @NotNull
    private String type;

    @NotNull
    private long amount;

    private LocalDateTime dateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    // Getters and Setters

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                '}';
    }

    // equals() and hashCode() can also be added here
}
