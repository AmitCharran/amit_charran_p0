package com.revature.repo;

import com.revature.util.ArrayList;
import com.revature.model.Transaction;
public interface TransactionDAO {

    // Create
    public void createTransaction(Transaction t);

    // Read
    public ArrayList<Transaction> retrieveAllTransactionByUser(String user);
    public ArrayList<Transaction> retrieveTransactionByTransactionType(String user, String aType);

    // Update
    // will not update transaction

    // Delete
    // will not delete transaction


}
