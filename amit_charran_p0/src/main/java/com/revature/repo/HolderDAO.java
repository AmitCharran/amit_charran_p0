package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.MyArrayList;

public interface HolderDAO {

    // Create
    public void createHolder(Account a);

    // Read
    public MyArrayList<Client> getAllClients(Account a);
    public MyArrayList<Integer> findAllClientAccountID(Client c);
    public int getHolderID(int accountID);
    // Update
    public void insertNewClient(Account a, Client c);
    public void removeAClient(Account a, Client c);

    // Delete
    public void deleteRow(Account a);

}
