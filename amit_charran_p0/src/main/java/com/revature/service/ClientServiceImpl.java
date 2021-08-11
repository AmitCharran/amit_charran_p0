package com.revature.service;

import com.revature.model.Client;
import com.revature.repo.ClientDAO;
import com.revature.repo.ClientDAOimpl;
import com.revature.util.MyArrayList;

public class ClientServiceImpl implements ClientService{
    private ClientDAO clientDAO = new ClientDAOimpl();

    @Override
    public void insertNewClient(Client c) {
        clientDAO.insertNewClient(c);
    }

    @Override
    public MyArrayList<Client> retrieveAllClients() {
        return clientDAO.retrieveAllClients();
    }

    @Override
    public Client retrieveClient(String username) {
        return clientDAO.retrieveClient(username);
    }

    @Override
    public Client retrieveClient(int client_id) {
        return clientDAO.retrieveClient(client_id);
    }

    @Override
    public boolean checkClientUsername(String username) {
        if(retrieveClient(username) == null){
            return false;
        }

        return true;
    }

    @Override
    public void updateFirstName(Client c, String firstName) {
        clientDAO.updateFirstName(c,firstName);
    }

    @Override
    public void updateLastName(Client c, String lastName) {
        clientDAO.updateLastName(c,lastName);
    }

    @Override
    public void updatePassword(Client c, String newPassword) {
        clientDAO.updatePassword(c,newPassword);
    }

    @Override
    public void deleteUser(Client c) {
        clientDAO.deleteUser(c);
    }
}
