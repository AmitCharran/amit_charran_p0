package com.revature.repo;

import com.revature.util.MyArrayList;
import com.revature.model.*;

/**
 * Accesses the account database and manipulates values in it
 */
public interface AccountDAO {

    /**
     * Inserts an account into the table
     * @param a Object from the account class
     */
    public void createAccount(Account a);


    /**
     * gets all account numbers associated with account.
     * It also adds account to the holder table to show other clients associated with the account
     * @param c Object from Client class
     * @return Returns an ArrayList of all accounts associated with a client
     */
    public MyArrayList<Account> returnAllAccounts(Client c);

    /**
     * Changes account number
     * @param a Object from account class
     * @param new_num new account number
     */
    public void changeAccountNumber(Account a, String new_num);

    /**
     * Checks if account number is already in our database
     * @param accNum account number to check
     * @return true or false
     */
    public boolean accNumberExist(String accNum);

    /**
     * Uses account_id to access account in database
     * @param accountID account_id
     * @return Account object from database
     */
    public Account retrieveAccount(int accountID);

    /**
     * An account can have five users associated with it.
     * This adds a client to an account
     * @param a Object from account class
     * @param c Object from client class
     */
    public void assignUserToAccount(Account a, Client c);

    /**
     * Removes money from an account
     * @param a Object from Account class
     * @param money Amount of money to be removes
     */
    public void withdraw(Account a, double money);

    /**
     * Adds money to an account
     * @param a Object from Account class
     * @param money Amount of money to add
     */
    public void deposit(Account a, double money);


    /**
     * Deletes row from account table
     * @param a Object from account class associated with account table row
     */
    public void deleteAccount(Account a);

}
