package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.repo.AccountDAO;
import com.revature.repo.AccountDAOimpl;
import com.revature.util.MyArrayList;

public class AccountServiceImpl implements AccountService{
   private AccountDAO aDAO = new AccountDAOimpl();

    @Override
    public void createAccount(Account a) {
        aDAO.createAccount(a);

    }

    public Account retrieveAccount(int accountID){
        return aDAO.retrieveAccount(accountID);
    }

    @Override
    public MyArrayList<Account> getAllAccounts(Client c) {
        return aDAO.returnAllAccounts(c);
    }

    @Override
    public void changeAccountNumber(Account a, String new_num) {
        aDAO.changeAccountNumber(a,new_num);
    }

    @Override
    public boolean accNumExist(String accNum) {
        return aDAO.accNumberExist(accNum);
    }

    @Override
    public void assignUserToAccount(Account a, Client c) {
        assignUserToAccount(a,c);

    }

    @Override
    public void withdraw(Account a, double money) {
        new AccountDAOimpl().withdraw(a, money);
    }

    @Override
    public void deposit(Account a, double money) {
        new AccountDAOimpl().deposit(a,money);
    }

    @Override
    public void deleteAccount(Account a) {
        new AccountDAOimpl().deleteAccount(a);

    }
}
