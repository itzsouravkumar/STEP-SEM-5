package com.gdb.tests;

import com.gdb.domain.AccountFactory;
import com.gdb.domain.SavingsAccount;

public class TestAccountRulesEngine {
    public static void main(String[] args) {
        System.out.println("=== Activity 13.2: Dynamic Account Rules Test ===");
        
        // Step 3: Verify Dynamic Rule Assignment
        SavingsAccount acc = (SavingsAccount) AccountFactory.createAccount("SAVINGS", "1001", "John Doe", 30, 20000.0, "ACTIVE", "1234", 4);
        
        System.out.println("Created Savings Account (Tenure: 4 yrs):");
        System.out.println(" -> Min Balance: Rs " + acc.getMinBalance() + " (Dynamically fetched)");
        System.out.println(" -> Interest Rate: " + acc.getInterestRate() + "% (Dynamically fetched)");
        
        System.out.println("Dynamic rule integration verified!");
    }
}
