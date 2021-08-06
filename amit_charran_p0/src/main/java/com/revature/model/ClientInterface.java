package com.revature.model;

public interface ClientInterface {
    public void viewAccount(); // probably change return type
    public void transferMoneyToOtherAccounts(Account a1, Account a2, double m);
}
