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
        String sql2 = "UPDATE account SET holder_id = ? WHERE account_id = ?";
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,holderID);
            ps.setInt(2, a.getAccount_id());
            ps.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public int getHolderID(int accountID){
        String sql = "SELECT holder_id FROM holder WHERE account_id = \'" + accountID + "\'";
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
        ArrayList<Client> cList = new ArrayList<>();

        ClientDAO clientDAO = new ClientDAOimpl();

        int account_id = new AccountDAOimpl().retrieveAccountID(a);
        int holderID = this.getHolderID(account_id);
        String sql = "SELECT * FROM holder WHERE holder_id = ?";

        int[] clients = new int[5];
        for(int i = 0; i < clients.length; i++){
            clients[i] = 0;
        }

        try(Connection connection = ConnectionFactory.getConnection()){
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setInt(1, (holderID));
         ResultSet rs = ps.executeQuery();

         while(rs.next()){
             clients[0] = rs.getInt(3);
             clients[1] = rs.getInt(4);
             clients[2] = rs.getInt(5);
             clients[3] = rs.getInt(6);
             clients[4]= rs.getInt(7);
         }


        }catch (SQLException e){
            e.printStackTrace();
        }

        for(int i = 0; i < clients.length; i++){
            if(clients[i] != 0){
                cList.add(clientDAO.retrieveClient(clients[i]));
            }
        }

        return cList;
    }

    @Override
    public void insertNewClient(Account a, Client c) {
        int accountID = new AccountDAOimpl().retrieveAccountID(a);
        int holderID = this.getHolderID(accountID);
        String sql = "SELECT * FROM holder WHERE holder_id = ?";

        Client c1 = new ClientDAOimpl().retrieveClient(c.getUsername());

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (holderID));
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                for(int i = 3; i <= 7; i++){
                    if(rs.getInt(i) == 0){
                        String client_column = clientIntMatch(i);
                        String sql2 = "UPDATE holder SET ? = ? WHERE holder_id = ?";

                        ps = connection.prepareStatement(sql2);
                        ps.setString(1, client_column);
                        ps.setInt(2, c1.getClientID());
                        ps.setInt(3,holderID);

                        break;
                    }

                    if(i == 7){
                        System.out.println("Cannot add, max account");
                    }
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private String clientIntMatch(int i){
        if(i == 3){
            return "client_one";
        }else if(i == 4){
            return "client_two";
        }else if(i == 5){
            return "client_three";
        }else if(i == 6){
            return "client_four";
        }else{
            return "client_five";
        }
    }

    @Override
    public void removeAClient(Account a, Client c) {
        
    }

    @Override
    public void deleteRow(Account a) {

    }
}