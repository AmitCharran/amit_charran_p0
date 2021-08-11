package com.revature.model;
import com.revature.repo.AccountDAOimpl;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;
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

    private int account_id;
    private String accountNumber; // account numbers need to be handled somehow
    // Most likely with SQL
    private AccountType type;
    private double balance;

    // Need to add a transactionHistory ArrayList
    private MyArrayList<Client> holders;
    public Account(){
        type = null;
        generateAccountNumber();
        balance = 0;
    }
    /**
     * Non-parameterized constructor.
     * Calls the one parameter constructor with balance of 0
     * @param type
     */
    public Account(AccountType type){
        this(type, 0);
        generateAccountNumber();
    }
    public Account(AccountType type, double balance){
        this.type = type;
        this.balance = balance;
        generateAccountNumber();
        // add function to create accountNumber appropriately
    }

    public Account(int account_id, String accountNumber, AccountType type, double balance, MyArrayList<Client> holders) {
        this.account_id = account_id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
        this.holders = holders;
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

    public boolean validForWithdraw(double n){
        if(n > this.balance || n < 0){
            return false;
        }
        return true;

    }
    public boolean validForDeposit(double n){
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

    public void generateAccountNumber(){
        boolean accountNumberExist = true;
        StringBuilder accNum = new StringBuilder();
        AccountService as = new AccountServiceImpl();

        while(accountNumberExist){
            for(int i = 0; i < 16; i++){
                accNum.append( (int)((Math.random() * 100) % 10));
            }

            String accNumber = accNum.toString();
            if(!as.accNumExist(accNumber)){
                accountNumberExist = false;
            }
        }

        accountNumber = accNum.toString();
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

    public String toString(){
        return "Account Number : " + accountNumber +
                "\nAccount Type : " + type +
                "\nBalance : $" + balance;
    }
}
