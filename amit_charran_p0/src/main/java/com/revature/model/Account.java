package com.revature.model;
import com.revature.util.AccountType;
import com.revature.util.MyArrayList;

/**
 * This is the account class for the banking app.
 * Use this to create banking accounts
 * @author Amit Charran
 * @version 1.0
 * @ Since 2021-08-3
 */
public class Account implements AccountInterface{
    private MyArrayList<Client> holders;
    private int account_id;
    private AccountType type;
    private double balance;
    private String accountNumber; // account numbers need to be handled somehow
                        // Most likely with SQL
    // Need to add a transactionHistory ArrayList


    /**
     * Non-parameterized constructor.
     * Calls the one parameter constructor with balance of 0
     * @param type
     */
    public Account(AccountType type){
        this(type, 0);
    }
    public Account(AccountType type, double balance){
        this.type = type;
        this.balance = balance;
        // add function to create accountNumber appropriately
    }

    /**
     * Checks if amount is valid for withdraw and allows it to be withdrawn
     * Then updates transaction history
     * @param n
     */
    public void withdraw(double n){
        if(!validForWithdraw(n)){
            System.out.println("not valid for withdraw");
            return;
        }

        balance = balance - n;
        // update transaction history
    }

    private boolean validForWithdraw(double n){
        if(n > this.balance || n < 0){
            return false;
        }
        return true;

    }
    private boolean validForDeposit(double n){
        if(n < 0){
            return false;
        }
        return true;
    }

    public void deposit(double n){
        if(!validForDeposit(n)){
            System.out.println("Not valid for deposit");
            // prints error message
            return;
        }

        balance = balance + n;
        // update transaction history
    }

    public MyArrayList<Client> getHolders() {
        return holders;
    }

    public void setHolders(MyArrayList<Client> holders) {
        this.holders = holders;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Create View Accounts Method
}
