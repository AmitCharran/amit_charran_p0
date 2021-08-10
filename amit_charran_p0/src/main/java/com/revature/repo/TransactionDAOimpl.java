package com.revature.repo;

import com.revature.model.*;
import com.revature.util.*;
import com.revature.util.ConnectionFactory;
import com.revature.util.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAOimpl implements TransactionDAO{
    @Override
    public void createTransaction(Transaction t) {
        String sql = "INSERT INTO bank_transaction" +
                " (transaction_date, transaction_amount, transaction_type, transaction_by, transfer_to_who)" +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setDate(1, t.getDate());
            ps.setDouble(2,t.getTransaction_amount());
            ps.setString(3,t.getType().toString());
            ps.setInt(4,t.getTransaction_by());
            ps.setInt(5,t.getTransfer_to_who());

            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public MyArrayList<Transaction> retrieveAllTransactionByAccount(Account a) {
        MyArrayList<Transaction> ans = new MyArrayList<>();
        int accountID = new AccountDAOimpl().retrieveAccountID(a);

        String sql = "SELECT * FROM bank_transaction WHERE transaction_by = ?";
        PreparedStatement ps;
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setInt(1,accountID);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ans.append(
                        new Transaction(
                                rs.getInt(1),
                                rs.getDate(2),
                                rs.getInt(3),
                                TransactionType.valueOf(rs.getString(4)),
                                rs.getInt(5),
                                rs.getInt(6)

                        )
                );

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return ans;
    }

    @Override
    public MyArrayList<Transaction> retrieveTransactionByTransactionType(Account a, TransactionType aType) {
        MyArrayList<Transaction> ans = new MyArrayList<>();
        int accountID = new AccountDAOimpl().retrieveAccountID(a);

        String sql = "SELECT * FROM bank_transaction WHERE transaction_by = ? and transaction_type = ?";
        PreparedStatement ps;
        try(Connection connection = ConnectionFactory.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setInt(1,accountID);
            ps.setString(2,aType.toString());


            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ans.append(
                        new Transaction(
                                rs.getInt(1),
                                rs.getDate(2),
                                rs.getInt(3),
                                TransactionType.valueOf(rs.getString(4)),
                                rs.getInt(5),
                                rs.getInt(6)

                        )
                );

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return ans;
    }

}
