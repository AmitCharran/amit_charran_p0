package com.revature.repo;

import com.revature.util.ArrayList;
import com.revature.model.Transaction;
public interface TransactionDAOInterface {

    // Create
    public void createTransaction(String date, double money, String transaction_type, String transaction_by, String transaction_to_who);

    // Read
    public ArrayList<Transaction> retieveAllTransactionByUser(String user);
    public ArrayList<Transaction> retrieveTransactionByTransactionType(String user, String aType);

    // Update
    // will not update transaction

    // Delete
    // will not delete transaction


}
