package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.ArrayList;
import com.revature.util.ConnectionFactory;

import java.sql.*;

public class HolderDAOimpl implements HolderDAO{
    @Override
    public void createHolder(Account a) {
        String sql = "INSERT INTO holder (account_id) VALUES (?)";
        PreparedStatement ps;

        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccount_id());
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

        int holderID = this.getHolderID(a.getAccount_id());
        String sql2 = "INSERT INTO account (holder_id) VALUES (?) WHERE account_id = " + a.getAccount_id();
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,holderID);
            ps.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public int getHolderID(int accountID){
        String sql = "SELECT account_id FROM account WHERE account_id = \'" + accountID + "\'";
        Statement s;
        int ans = -1;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                ans  = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ans;
    }

    @Override
    public ArrayList<Client> getAllClients(Account a) {
        return null;
    }

    @Override
    public void insertNewClient(Account a, Client c) {

    }

    @Override
    public void removeAClient(Account a, Client c) {

    }

    @Override
    public void deleteRow(Account a) {

    }
}
