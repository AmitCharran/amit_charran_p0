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
    public void updateFirstName(Client c, String firstName);
    public void updateLastName(Client c,String lastName);
    public void updatePassword(Client c, String newPassword);

    //DELETE
    public void deleteUser(Client c);

}
