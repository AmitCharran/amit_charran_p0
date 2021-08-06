package com.revature.util;

import com.revature.secret.secretValues;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = secretValues.endpoint;
    private static final String USERNAME = secretValues.username;
    private static final String PASSWORD = secretValues.password;

    private static Connection connection;

    public static Connection getConnection(){

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;

    }

}
