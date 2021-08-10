package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.repo.TransactionDAO;
import com.revature.repo.TransactionDAOimpl;
import com.revature.util.MyArrayList;
import com.revature.util.TransactionType;

public class TransactionServiceImpl implements TransactionService{
    TransactionDAO tDAO = new TransactionDAOimpl();
    @Override
    public void createTransaction(Transaction t) {
        tDAO.createTransaction(t);
    }

    @Override
    public MyArrayList<Transaction> retrieveAllTransactionByAccount(Account a) {
        return tDAO.retrieveAllTransactionByAccount(a);
    }

    @Override
    public MyArrayList<Transaction> retrieveTransactionByTransactionType(Account a, TransactionType aType) {
        return tDAO.retrieveTransactionByTransactionType(a,aType);
    }
}
