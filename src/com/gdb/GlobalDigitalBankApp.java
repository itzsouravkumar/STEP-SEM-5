package com.gdb;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class GlobalDigitalBankApp {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   GLOBAL DIGITAL BANK - STEP :)   ");
        System.out.println("==================================================\n");

        try {
            // 1. Factory Pattern & Dynamic Rules Engine (Activities 12, 13)
            System.out.println(">>> 1. Creating Accounts via Factory Pattern");
            
            // Creating a new customer (Tenure: 0 yrs) -> Min balance 10000, 2.7% interest
            SavingsAccount newCustomerAcc = (SavingsAccount) AccountFactory.createAccount(
                "SAVINGS", "SAV-001", "Alice Newbie", 22, 15000.0, "ACTIVE", "1111", 0
            );
            
            // Creating a privilege customer (Tenure: 6 yrs) -> Min balance 2500, 4.0% interest
            SavingsAccount privilegeAcc = (SavingsAccount) AccountFactory.createAccount(
                "SAVINGS", "SAV-002", "Bob Veteran", 45, 50000.0, "ACTIVE", "2222", 6
            );
            
            // Creating a Current Account
            CurrentAccount businessAcc = (CurrentAccount) AccountFactory.createAccount(
                "CURRENT", "CUR-001", "Charlie Corp", 35, 100000.0, "ACTIVE", "3333", 2
            );

            System.out.println("Accounts successfully created with Dynamic Rules applied!");
            System.out.println("Alice (0 yrs) Min Balance: Rs " + newCustomerAcc.getMinBalance() + " | Interest: " + newCustomerAcc.getInterestRate() + "%");
            System.out.println("Bob (6 yrs)   Min Balance: Rs " + privilegeAcc.getMinBalance() + " | Interest: " + privilegeAcc.getInterestRate() + "%\n");

            // 2. Exception Handling & Business Logic (Activity 5 & 6)
            System.out.println(">>> 2. Exception Handling (Insufficient Balance)");
            try {
                System.out.println("Attempting to withdraw Rs 10000 from Alice's account (Balance: Rs 15000, Min Bal: Rs 10000)");
                newCustomerAcc.withdraw(10000.0, "1111"); // This should fail because 15000 - 10000 = 5000 (breaches min bal 10000)
            } catch (AccountException e) {
                System.out.println("EXPECTED EXCEPTION CAUGHT: " + e.getMessage() + "\n");
            }

            // 3. Security (PIN verification)
            System.out.println(">>> 3. Security (PIN Verification)");
            try {
                System.out.println("Attempting to withdraw with wrong PIN...");
                privilegeAcc.withdraw(1000.0, "9999");
            } catch (AccountException e) {
                System.out.println("EXPECTED EXCEPTION CAUGHT: " + e.getMessage() + "\n");
            }

            // 4. Polymorphism & Collections (Activity 7 & 8)
            System.out.println(">>> 4. Polymorphism (Processing all accounts)");
            IAccount[] allAccounts = {newCustomerAcc, privilegeAcc, businessAcc};
            
            double totalBankFunds = 0;
            for (IAccount acc : allAccounts) {
                // We cast to AbstractAccount just to use print properties
                if (acc instanceof AbstractAccount) {
                    AbstractAccount baseAcc = (AbstractAccount) acc;
                    System.out.println(baseAcc.getAccountType() + " [" + baseAcc.getAccountNumber() + "] - " 
                        + baseAcc.getName() + " | Balance: Rs " + baseAcc.getBalance());
                    totalBankFunds += baseAcc.getBalance();
                }
            }
            System.out.println("Total Bank Funds Managed: Rs " + totalBankFunds);
            System.out.println("\n==================================================");
            System.out.println("   SHOWCASE COMPLETED SUCCESSFULLY!               ");
            System.out.println("==================================================");

        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }
}
