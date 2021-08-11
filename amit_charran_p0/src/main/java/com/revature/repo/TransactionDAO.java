package com.revature.repo;

import com.revature.util.MyArrayList;
import com.revature.model.*;
import com.revature.util.TransactionType;

/**
 * Methods used to access and manipulate the bank_transaction table
 */
public interface TransactionDAO {

    /**
     * Inserts transaction to bank_transaction table
     * @param t Object from Transaction class
     */
    public void createTransaction(Transaction t);

    /**
     * returns all transaction by account from arrayList
     * @param a Object from Account class
     * @return a list of all transaction associated with an account
     */
    public MyArrayList<Transaction> retrieveAllTransactionByAccount(Account a);

    /**
     * returns all transaction by account and account type from arrayList
     * @param a Object from Account class
     * @param aType
     * @return a list of all transaction associated with an account and account type
     */
    public MyArrayList<Transaction> retrieveTransactionByTransactionType(Account a, TransactionType aType);

    // Update
    // will not update transaction

    // Delete
    // will not delete transaction


}
