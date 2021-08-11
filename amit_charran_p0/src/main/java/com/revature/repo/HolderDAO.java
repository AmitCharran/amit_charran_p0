package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.MyArrayList;

public interface HolderDAO {

    /**
     * Inserts a row to holder table
     * @param a Object from account class.
     *          A new row is created for at most five clients to be associated with an account
     */
    public void createHolder(Account a);

    /**
     * retrieve all clients associated with an acocunt
     * @param a Object from account class
     * @return an arrayList of clients
     */
    public MyArrayList<Client> getAllClients(Account a);

    /**
     * Retrieve all accountID associated with a client
     * @param c Object from client class
     * @return an arrayList of Integer, all integers are account_ids
     */
    public MyArrayList<Integer> findAllClientAccountID(Client c);

    /**
     * returns the holder_id associated with acoount_id
     * @param accountID primary_key from account table
     * @return primary_key from holder table
     */
    public int getHolderID(int accountID);

    /**
     * Uses accountID and clientID to update an account row in holder table
     * @param a Account object
     * @param c Client object
     */
    public void insertNewClient(Account a, Client c);
    public void removeAClient(Account a, Client c);

    /**
     * deletes a row in this table
     * @param a
     */
    public void deleteRow(Account a);

}
