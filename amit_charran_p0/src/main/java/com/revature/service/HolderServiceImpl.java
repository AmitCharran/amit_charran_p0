package com.revature.service;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.repo.HolderDAO;
import com.revature.repo.HolderDAOimpl;
import com.revature.util.MyArrayList;

public class HolderServiceImpl implements HolderService {
    private HolderDAO holderDAO = new HolderDAOimpl();

    @Override
    public void createHolder(Account a) {
        holderDAO.createHolder(a);
    }

    @Override
    public int getHolderID(int accountID) {
        return holderDAO.getHolderID(accountID);
    }

    @Override
    public MyArrayList<Client> getAllClients(Account a) {
        return holderDAO.getAllClients(a);
    }

    @Override
    public MyArrayList<Integer> findAllClientAccountID(Client c) {
        return holderDAO.findAllClientAccountID(c);
    }

    @Override
    public void insertNewClient(Account a, Client c) {
        holderDAO.insertNewClient(a,c);
    }

    @Override
    public void removeAClient(Account a, Client c) {
        holderDAO.removeAClient(a,c);
    }

    @Override
    public void deleteRow(Account a) {
        holderDAO.deleteRow(a);
    }
}
