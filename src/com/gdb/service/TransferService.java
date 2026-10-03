package com.gdb.service;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TransferService {

    public static void transfer(IAccount fromAcc, IAccount toAcc, double amount, String pin) throws AccountException {
        if (fromAcc == null || toAcc == null) {
            throw new AccountException("Source and destination accounts are required");
        }
        if (!"ACTIVE".equalsIgnoreCase(fromAcc.getStatus()) || !"ACTIVE".equalsIgnoreCase(toAcc.getStatus())) {
            throw new InactiveAccountException("Both accounts must be active to transfer funds");
        }
        
        AbstractAccount from = (AbstractAccount) fromAcc;
        AbstractAccount to = (AbstractAccount) toAcc;
        
        if (!from.validatePin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }
        
        if (from.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for transfer of Rs. " + amount);
        }
        
        from.resetDailyTransferIfNeeded();
        if (!from.canTransfer(amount)) {
            double remaining = Math.max(0.0, from.getDailyTransferLimit() - from.getDailyTransferTotal());
            throw new AccountException("Daily transfer limit exceeded. Remaining today: Rs. " + remaining);
        }
        
        from.withdraw(amount, pin);
        to.deposit(amount);
        from.updateDailyTransferTotal(amount);
    }

    public static Transaction transferWithTransaction(IAccount fromAcc, IAccount toAcc, double amount, String pin) throws AccountException {
        transfer(fromAcc, toAcc, amount, pin);
        AbstractAccount from = (AbstractAccount) fromAcc;
        AbstractAccount to = (AbstractAccount) toAcc;
        return new Transaction(
            Transaction.generateId(),
            java.time.LocalDateTime.now(),
            from.getAccountNumber(),
            TransactionType.TRANSFER, amount, from.getBalance(), "SUCCESS",
            "Transfer of Rs. " + amount + " to Account #" + to.getAccountNumber(),
            from.getAccountNumber(), to.getAccountNumber()
        );
    }
}
