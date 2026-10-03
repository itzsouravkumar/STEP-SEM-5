package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;
import com.gdb.exceptions.*;

public class TestTransfer {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 15 — TRANSFER WITH DAILY LIMITS");
        System.out.println("=".repeat(60));

        TransferService svc = new TransferService();
        AccountRulesEngine engine = AccountRulesEngine.getInstance();

        AbstractAccount acc1 = (AbstractAccount) AccountFactory.createAccount("SAVINGS", "1001", "Rajesh Sharma", 30, 100000, "ACTIVE", "1234", 0);
        AbstractAccount acc2 = (AbstractAccount) AccountFactory.createAccount("SAVINGS", "1002", "Priya Patel", 28, 20000, "ACTIVE", "1234", 0);
        System.out.println("[STEP 9] Account #1001 | Rajesh Sharma (30 yrs, Tenure: 0 yrs) | Savings | Rs. 100000.0 | Active");
        System.out.println("[STEP 9] Account #1002 | Priya Patel (28 yrs, Tenure: 0 yrs) | Savings | Rs. 20000.0 | Active");
        System.out.println();

        try {
            TransferService.transfer(acc1, acc2, 5000, "1234");
            System.out.println("[STEP 10] Transfer Rs. 5,000: SUCCESS | acc1 = Rs. " + acc1.getBalance() + " | acc2 = Rs. " + acc2.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            TransferService.transfer(acc1, acc2, 100000, "1234");
        } catch (InsufficientBalanceException e) {
            System.out.println("[STEP 11] Caught InsufficientBalanceException: " + e.getMessage());
        }

        System.out.println("[STEP 12] Daily limit for acc1: Rs. " + acc1.getDailyTransferLimit());
        try {
            int count = 1;
            while (true) {
                TransferService.transfer(acc1, acc2, 20000, "1234");
                System.out.println("  Transfer #" + count + " of Rs. 20,000: SUCCESS | used today = Rs. " + acc1.getDailyTransferTotal());
                count++;
            }
        } catch (AccountException e) {
            System.out.println("[STEP 12] Caught AccountException: " + e.getMessage());
        }

        System.out.println("[STEP 13] Used today: Rs. " + acc1.getDailyTransferTotal() + " | Remaining: Rs. " + acc1.getRemainingDailyTransferLimit());
    }
}
