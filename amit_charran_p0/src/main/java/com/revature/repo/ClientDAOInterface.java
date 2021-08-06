package com.revature.repo;

import com.revature.model.*;
import com.revature.util.ArrayList;

public interface ClientDAOInterface {

    //CREATE
    public void insertNewClient(Client c);


    //READ
    public ArrayList<Client> retrieveAllClients(Client c);


    //UPDATE

    //DELETE

}
