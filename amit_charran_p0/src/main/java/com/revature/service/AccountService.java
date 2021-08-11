package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.MyArrayList;

public interface AccountService {
    public void createAccount(Account a);
    public MyArrayList<Account> getAllAccounts(Client c);

    public void changeAccountNumber(Account a, String new_num);
    public boolean accNumExist(String accNum);
    public void assignUserToAccount(Account a, Client c);
    public void withdraw(Account a, double money);
    public void deposit(Account a, double money);

    public void deleteAccount(Account a);



}
