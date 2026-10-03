package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;
import com.gdb.exceptions.*;

public class TestTransactionModel {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 16 — TRANSACTION MODEL TEST");
        System.out.println("=".repeat(60));

        TransferService svc = new TransferService();

        AbstractAccount acc1 = (AbstractAccount) AccountFactory.createAccount("SAVINGS", "1001", "Rajesh Sharma", 30, 50000, "ACTIVE", "1234", 0);
        System.out.println("[STEP 10] Deposit Transaction: " + acc1.depositWithTransaction(5000));

        System.out.println("[STEP 11] Withdrawal Transaction: " + acc1.withdrawWithTransaction(2000, "1234"));

        AbstractAccount acc2 = (AbstractAccount) AccountFactory.createAccount("SAVINGS", "1002", "Priya Patel", 28, 20000, "ACTIVE", "1234", 0);
        System.out.println("[STEP 12] Transfer Transaction: " + TransferService.transferWithTransaction(acc1, acc2, 1000, "1234"));

        acc1.deposit(1000);
        System.out.println("[STEP 13] Legacy Deposit +1000: Account #" + acc1.getAccountNumber() + " | " + acc1.getName() + " (30 yrs, Tenure: 0 yrs) | " + acc1.getAccountType() + " | Rs. " + acc1.getBalance() + " | Active");
    }
}
