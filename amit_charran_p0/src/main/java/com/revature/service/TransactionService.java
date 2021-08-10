package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.util.MyArrayList;
import com.revature.util.TransactionType;

public interface TransactionService {
    // Create
    public void createTransaction(Transaction t);

    // Read
    public MyArrayList<Transaction> retrieveAllTransactionByAccount(Account a);
    public MyArrayList<Transaction> retrieveTransactionByTransactionType(Account a, TransactionType aType);

}
