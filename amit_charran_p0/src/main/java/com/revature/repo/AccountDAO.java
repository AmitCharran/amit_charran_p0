package com.revature.repo;

import com.revature.util.ArrayList;
import com.revature.model.*;

public interface AccountDAO {
    // Create
    public void createAccount(Account a);

    // Read
    public ArrayList<Account> returnAllAccounts(Client c);

    // Update
    public void changeAccountNumber(Account a);
    public void assignUserToAccount(Account a, Client c);
    public void withdraw(Account a, double money);
    public void deposit(Account a, double money);

    // Delete
    public void deleteAccount(Account a);

}
