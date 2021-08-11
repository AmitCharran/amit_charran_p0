package com.revature.repo;

import com.revature.util.MyArrayList;
import com.revature.model.*;

public interface AccountDAO {
    // Create
    public void createAccount(Account a);

    // Read
    public MyArrayList<Account> returnAllAccounts(Client c);

    // Update
    public void changeAccountNumber(Account a, String new_num);
    public boolean accNumberExist(String accNum);
    public Account retrieveAccount(int accountID);
    public void assignUserToAccount(Account a, Client c);
    public void withdraw(Account a, double money);
    public void deposit(Account a, double money);


    // Delete
    public void deleteAccount(Account a);

}
