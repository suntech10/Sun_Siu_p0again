package com.revature;

import java.io.Serializable;

public class Accounts implements Serializable { // a Java Bean
    private static int accNumNext = 1000; // Start off at 1000 to reserve a block of low-numbered accounts for fixing problems; use this as the account number for the next account to be opened

    Accounts() {
    } // Null constructor to make it a Java Bean

    // Constructor for Accounts with initial balance given
    Accounts(double balance) {
        System.out.println("Opening acccount with balance " + balance);
        this.balance = balance;
    }

    private int accNum=1000;
    private double balance = 0.0;
    private String username;
    private String password;

    public void setAccNumNext(int accNumNext) {
        this.accNumNext = accNumNext;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public int getAccNum() {
        return this.accNum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public double deposit(int accNum, double depAmt) {
        if (accNum < 1000 || accNum > accNumNext) {
            System.out.println("Found illegal account number, deposit is ignored");
        }
        // update the balance of the account with the account number accNum
        setBalance(getBalance() + depAmt);

        return getBalance();
    }
    /*
        // Locate the index of the account with the specified account number; returns -1 if cannot locate it
        public static int locateAcc(ArrayList aryAcc, int accNum) {
            // System.out.println("Searching for the account with the number " + accNum);

            int size = aryAcc.size();
            // System.out.println("Size of ArrayList of accounts is " + size);
            int found = -1; // start out with not found
            for (int i = size - 1; i >= 0; i--)     { // search in reverse to favor newer accounts
                Accounts acc = aryAcc.get(i);
                // System.out.println("For index " + i + ", checking if account number " + acc.accNum + " matches target of " + accNum);
                if ( acc.accNum == accNum) {
                    found = i;
                    break;
                }
            }

            return found;
        }
    */
    public void setUserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserLoginName() {
        return this.username;
    }
    public String getUserLoginPassword() {
        return this.password;
    }

    public int getAccNumNext() {
        return accNumNext;
    }

    public int incAccNumNext() {
        accNumNext++;
        return accNumNext;
    }
}
