package com.revature.repo;

import com.revature.util.ArrayList;
import com.revature.model.*;

public interface AccountDAOInterface {
    // Create
    public void createAccount(String accountAlias, String accountNumber, String accountType, double balance, ArrayList<Client> holder);

    // Read
    public ArrayList<Account> returnAllAccounts(Client c);

    // Update
    public void changeAccountNumber(Client c);
    public void withdraw(double money);
    public void deposit(double money);

    // Delete
    public void deleteAccount(Account a);

}
