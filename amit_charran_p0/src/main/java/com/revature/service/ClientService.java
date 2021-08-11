package com.revature.service;

import com.revature.model.Client;
import com.revature.util.MyArrayList;

public interface ClientService {
    //CREATE
    public void insertNewClient(Client c);

    //READ
    public MyArrayList<Client> retrieveAllClients();
    public Client retrieveClient(String username);
    public Client retrieveClient(int client_id);
    public boolean checkClientUsername(String username);

    //UPDATE
    public void updateFirstName(Client c, String firstName);
    public void updateLastName(Client c,String lastName);
    public void updatePassword(Client c, String newPassword);

    //DELETE
    public void deleteUser(Client c);
}
