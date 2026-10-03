package com.gdb.domain;

import com.gdb.exceptions.*;
import java.time.LocalDateTime;

public abstract class AbstractAccount implements IAccount, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected String accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected String pin;
    
    protected int tenureYears = 0;
    protected double dailyTransferTotal = 0.0;
    protected LocalDateTime lastTransferDate = LocalDateTime.now();

    protected AbstractAccount() {}

    public AbstractAccount(String accountNumber, String name, int age, double balance, String accountType, String status, String pin) {
        if (age < 18) throw new IllegalArgumentException("Customer age must be 18 or above");
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        if (pin == null || !pin.matches("\\d{4}")) throw new IllegalArgumentException("PIN must be 4 digits");
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = status;
        this.pin = pin;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin != null && this.pin.equals(enteredPin);
    }

    public boolean changePin(String oldPin, String newPin) {
        if (!validatePin(oldPin)) return false;
        if (newPin == null || !newPin.matches("\\d{4}")) return false;
        this.pin = newPin;
        return true;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount, String enteredPin) throws AccountException {
        if (!validatePin(enteredPin)) throw new InvalidPinException("Invalid PIN entered");
        if (!"ACTIVE".equalsIgnoreCase(this.status)) throw new InactiveAccountException("Account is not active");
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        processDebit(amount);
    }

    public abstract void processDebit(double amount) throws AccountException;

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Status: " + status);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public String getStatus() { return status; }

    public int getTenureYears() { return tenureYears; }
    public void setTenureYears(int tenureYears) { this.tenureYears = Math.max(0, tenureYears); }

    public double getDailyTransferLimit() {
        return AccountRulesEngine.getInstance().getDailyTransferLimit(getAccountType(), getTenureYears());
    }

    public double getRemainingDailyTransferLimit() {
        resetDailyTransferIfNeeded();
        return Math.max(0.0, getDailyTransferLimit() - dailyTransferTotal);
    }

    public boolean canTransfer(double amount) {
        resetDailyTransferIfNeeded();
        return (dailyTransferTotal + amount) <= getDailyTransferLimit();
    }

    public void updateDailyTransferTotal(double amount) {
        resetDailyTransferIfNeeded();
        dailyTransferTotal += amount;
        lastTransferDate = LocalDateTime.now();
    }

    public void resetDailyTransferIfNeeded() {
        if (lastTransferDate == null || !lastTransferDate.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
            dailyTransferTotal = 0.0;
            lastTransferDate = LocalDateTime.now();
        }
    }

    public double getDailyTransferTotal() { return dailyTransferTotal; }
    public LocalDateTime getLastTransferDate() { return lastTransferDate; }

    protected Transaction buildTransaction(TransactionType type, double amount, 
                                           String fromAcc, String toAcc, String desc) {
        return new Transaction(
            Transaction.generateId(),
            LocalDateTime.now(),
            this.accountNumber,
            type, amount, this.balance, "SUCCESS",
            desc, fromAcc, toAcc
        );
    }

    public Transaction depositWithTransaction(double amount) throws AccountException {
        deposit(amount);
        return buildTransaction(TransactionType.DEPOSIT, amount, null, null, "Deposit of Rs. " + amount);
    }

    public Transaction withdrawWithTransaction(double amount, String pin) throws AccountException {
        withdraw(amount, pin);
        return buildTransaction(TransactionType.WITHDRAW, amount, null, null, "Withdrawal of Rs. " + amount);
    }
}
