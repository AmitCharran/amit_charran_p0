package com.revature.repo;

import com.revature.model.Transaction;
import com.revature.util.ArrayList;

public class TransactionDAOimpl implements TransactionDAO{
    @Override
    public void createTransaction(Transaction t) {

    }

    @Override
    public ArrayList<Transaction> retrieveAllTransactionByUser(String user) {
        return null;
    }

    @Override
    public ArrayList<Transaction> retrieveTransactionByTransactionType(String user, String aType) {
        return null;
    }
}
