package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.ArrayList;

public interface HolderDAO {

    // Create
    public void createHolder(Account a);

    // Read
    public ArrayList<Client> getAllClients(Account a);

    // Update
    public void insertNewClient(Account a, Client c);
    public void removeAClient(Account a, Client c);

    // Delete
    public void deleteRow(Account a);

}
