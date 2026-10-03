package com.gdb.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private static int counter = 0;

    private String transactionId;
    private LocalDateTime timestamp;
    private String accountNumber;
    private TransactionType type;
    private double amount;
    private double balanceAfter;
    private String status;
    private String description;
    private String fromAccount;
    private String toAccount;

    public Transaction(String transactionId, LocalDateTime timestamp, String accountNumber, TransactionType type, double amount, double balanceAfter, String status, String description, String fromAccount, String toAccount) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.status = status;
        this.description = description;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getBalanceAfter() { return balanceAfter; }
    public void setBalanceAfter(double balanceAfter) { this.balanceAfter = balanceAfter; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }

    public static String generateId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (++counter);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Rs. %.1f | Balance After: Rs. %.1f | Status: %s | %s",
                transactionId, type, amount, balanceAfter, status, description);
    }
}
