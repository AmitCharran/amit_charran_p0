package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.MyArrayList;

import java.sql.*;

import com.revature.util.*;

public class ClientDAOimpl implements ClientDAO {

    @Override
    public void insertNewClient(Client c) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO client (first_name, last_name, username, pass_word) values (?, ?, ?, ?)";

        PreparedStatement ps;

        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getUsername());
            ps.setString(4, c.getPassword());
            ps.execute();
        }catch (SQLException e){
            System.out.println("Error adding new client to table");
            e.printStackTrace();
        }

    }

    @Override
    public MyArrayList<Client> retrieveAllClients() {
        Connection connection = ConnectionFactory.getConnection();

        MyArrayList<Client> clientList = new MyArrayList<>();

        String sql = "SELECT * FROM client";
        Statement s;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                clientList.append(
                        new Client(
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(1),
                                null) // need to find a way to list all account numbers
                );
            }

         connection.close();
        }catch (SQLException e)
        {
            System.out.println("Problem with retrieving all clients");
            e.printStackTrace();
        }
        return clientList;

    }

    // need to fix
    private MyArrayList<Account> getAllAssociatedAccounts(int holder_id){
        MyArrayList<Account> ans = new MyArrayList<>();


        return null;
    }

    @Override
    public Client retrieveClient(String username) {
        String sql = "SELECT * FROM client WHERE username = ?";
        Client c = null;

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                c = new Client(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(1),
                        null); // need to create function
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Client retrieveClient(int client_id) {
        String sql = "SELECT * FROM client WHERE client_id = ?";
        Client c = null;

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, client_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                c = new Client(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(1),
                        null); // need to create function
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public void updateFirstName(Client c, String firstName) {
        String sql = "UPDATE client SET first_name = " + firstName + " WHERE client_id = " + c.getClientID();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateLastName(Client c, String lastName) {
        String sql = "UPDATE client SET last_name = " + lastName + " WHERE client_id = " + c.getClientID();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(Client c, String newPassword) {
        String sql = "UPDATE client SET pass_word = " + newPassword + " WHERE client_id = " + c.getClientID();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Client c) {
        String sql = "DELETE FROM client WHERE client_id = " + c.getClientID();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
