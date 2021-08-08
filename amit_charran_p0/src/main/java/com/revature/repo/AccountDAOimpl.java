package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.ArrayList;
import com.revature.util.ConnectionFactory;

import java.sql.*;

public class AccountDAOimpl implements AccountDAO {
    @Override
    public void createAccount(Account a) {
         String sql = "INSERT INTO account (account_number, account_type, balance) values (?, ?, ?)";
         String sql2 = "SELECT account_id FROM account WHERE account_number = "+ a.getAccountNumber();
        PreparedStatement ps;


        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setString(1,a.getAccountNumber());
            ps.setString(2,a.getType().toString());
            ps.setDouble(3,a.getBalance());
            ps.execute();

            ResultSet rs = ps.executeQuery(sql2);
            int id = rs.getInt(1);
            a.setAccount_id(id);


            // here I want to get
            HolderDAO h = new HolderDAOimpl();
            h.createHolder(a);



        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public int retrieveAccountID(Account a){
        String sql = "SELECT account_id FROM account WHERE account_number = \'"+ a.getAccountNumber()+"\'";
        Statement s;
        int ans = -1;

        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()) {
                ans = rs.getInt(1);
            }
            a.setAccount_id(ans);


            // here I want to get
            HolderDAO h = new HolderDAOimpl();
            h.createHolder(a);



        }catch (SQLException e){
            e.printStackTrace();
        }
        return ans;

    }

    @Override
    public ArrayList<Account> returnAllAccounts(Client c) {
        return null;
    }

    @Override
    public void changeAccountNumber(Account a) {
        String sql = "UPDATE account SET account_number = " + a.getAccountNumber() + " WHERE account_id = " + a.getAccount_id();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void assignUserToAccount(Account a, Client c) {
        // maybe make this boolean because account can only have 5 users
    }

    @Override
    public void withdraw(Account a, double money) {
        a.withdraw(money);
        String sql = "UPDATE account SET money = " + a.getBalance() + " WHERE account_id = " + a.getAccount_id();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deposit(Account a, double money) {
        a.deposit(money);
        String sql = "UPDATE account SET money = " + a.getBalance() + " WHERE account_id = " + a.getAccount_id();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAccount(Account a) {
        String sql = "DELETE FROM account WHERE account_id = " + a.getAccount_id();
        Statement s;
        try(Connection connection = ConnectionFactory.getConnection()){
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
