package com.revature.repo;

import com.revature.model.*;
import com.revature.util.ArrayList;

public interface ClientDAOInterface {

    //CREATE
    public void insertNewClient(Client c);

    //READ
    public ArrayList<Client> retrieveAllClients();
    public Client retrieveClient(String username);

    //UPDATE
    public void updateFirstName(String firstName);
    public void updateLastName(String lastName);
    public void updatePassword(String newPassword);

    //DELETE
    public void deleteUser(String username, String password);

}
