package com.gdb.command;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;

public class WithdrawCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    // ============================================================
    private IAccount account;
    private double amount;
    private String pin;
    private Transaction transaction;

    // ============================================================
    public WithdrawCommand(IAccount account, double amount, String pin) {
        this.account = account;
        this.amount = amount;
        this.pin = pin;
    }

    // ============================================================
    @Override
    public void execute() throws Exception {
        this.transaction = ((com.gdb.domain.AbstractAccount) account).withdrawWithTransaction(amount, pin);
    }

    // ============================================================
    @Override
    public Transaction getTransaction() {
        return this.transaction;
    }
}
