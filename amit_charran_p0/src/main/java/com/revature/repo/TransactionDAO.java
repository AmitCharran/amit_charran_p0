package com.revature.repo;

import com.revature.util.MyArrayList;
import com.revature.model.*;
import com.revature.util.TransactionType;

public interface TransactionDAO {

    // Create
    public void createTransaction(Transaction t);

    // Read
    public MyArrayList<Transaction> retrieveAllTransactionByAccount(Account a);
    public MyArrayList<Transaction> retrieveTransactionByTransactionType(Account a, TransactionType aType);

    // Update
    // will not update transaction

    // Delete
    // will not delete transaction


}
