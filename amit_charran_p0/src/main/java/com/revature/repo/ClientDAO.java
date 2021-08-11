package com.revature.repo;

import com.revature.model.*;
import com.revature.util.MyArrayList;

/**
 * Methods used to access and manipulate the client table
 */
public interface ClientDAO {

    /**
     * Inserts new client to client table
     * @param c Object from client class
     */
    public void insertNewClient(Client c);

    /**
     * returns all clients associated with client class
     * @return an ArrayList of all clients
     */
    public MyArrayList<Client> retrieveAllClients();
    public Client retrieveClient(String username);
    public Client retrieveClient(int client_id);

    /**
     * Allows the client to change their first name
     * @param c Object from client class
     * @param firstName name to be changed
     */
    public void updateFirstName(Client c, String firstName);

    /**
     * Allows the client to change their last name
     * @param c Object from client class
     * @param lastName  name to be changed
     */
    public void updateLastName(Client c,String lastName);

    /**
     * Allows the client to change their password
     * @param c Object from client class
     * @param newPassword password to be changed
     */
    public void updatePassword(Client c, String newPassword);

    /**
     * deletes a row from client table
     * @param c object from client class to be deleted
     */
    public void deleteUser(Client c);

}
