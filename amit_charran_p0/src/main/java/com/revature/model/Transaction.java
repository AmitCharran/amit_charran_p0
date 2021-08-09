package com.revature.model;

import com.revature.util.TransactionType;

import java.util.Date;

public class Transaction {
    private int transactionID; // get this from SQL table
    private Date date;
    private double transaction_amount;
    private TransactionType type;
    private int transaction_by;
    private int transfer_to_who; // myself or null

    public Transaction(){}

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getTransaction_by() {
        return transaction_by;
    }

    public void setTransaction_by(int transaction_by) {
        this.transaction_by = transaction_by;
    }

    public int getTransfer_to_who() {
        return transfer_to_who;
    }

    public void setTransfer_to_who(int transfer_to_who) {
        this.transfer_to_who = transfer_to_who;
    }
}
