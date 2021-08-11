package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.AccountType;
import com.revature.util.MyArrayList;
import com.revature.util.ConnectionFactory;
import com.revature.util.TransactionType;

import java.sql.*;

public class AccountDAOimpl implements AccountDAO {

    @Override
    public void createAccount(Account a) {
         String sql = "INSERT INTO account (account_number, account_type, balance) values (?, ?, ?)";
         String sql2 = "SELECT account_id FROM account WHERE account_number = ?";
        PreparedStatement ps;


        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setString(1,a.getAccountNumber());
            ps.setString(2,a.getType().toString());
            ps.setDouble(3,a.getBalance());
            ps.execute();


            ps = connection.prepareStatement(sql2);
            ps.setString(1, a.getAccountNumber());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt(1);
                a.setAccount_id(id);
            }


            // here I want to get
            HolderDAO h = new HolderDAOimpl();
            h.createHolder(a);


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * returns account_id when given an account
     * @param a Object from account class, but uses only account number from that class for this
     * @return int - account_id number from table
     */
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

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ans;

    }

    @Override
    public Account retrieveAccount(int accountID){
        Account acc = new Account();
        String sql = "SELECT * FROM account WHERE account_id = ?";
        PreparedStatement ps;

        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                acc.setAccount_id(rs.getInt(1));
                acc.setAccountNumber(rs.getString(2));
                acc.setType(AccountType.valueOf(rs.getString(3)));
                acc.setBalance(rs.getDouble(4));
                acc.setHolders(new HolderDAOimpl().getAllClientsWithAccID(accountID));
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean accNumberExist(String accNum){
        String sql = "SELECT * FROM account WHERE account_number = ?";
        PreparedStatement ps;
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setString(1, accNum);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MyArrayList<Account> returnAllAccounts(Client c) {
        MyArrayList<Integer> accountIDs = new HolderDAOimpl().findAllClientAccountID(c);
        MyArrayList<Account> ans = new MyArrayList<>();

        for(int i = 0; i < accountIDs.size(); i++){
            ans.append(retrieveAccount(accountIDs.get(i)));
        }
        return ans;
    }

    @Override
    public void changeAccountNumber(Account a, String new_num) {
        String sql = "UPDATE account SET account_number = " + new_num + " WHERE account_id = " + a.getAccount_id();
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
       new HolderDAOimpl().insertNewClient(a,c);
    }

    @Override
    public void withdraw(Account a, double money) {
        a.withdraw(money);
        String sql = "UPDATE account SET balance = " + a.getBalance() + " WHERE account_id = " + a.getAccount_id();
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
        String sql = "UPDATE account SET balance = " + a.getBalance() + " WHERE account_id = " + a.getAccount_id();
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
        if(a.getAccount_id() == 0){
            int ab = this.retrieveAccountID(a);
            a.setAccount_id(ab);
        }

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
