package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;
    private static Properties properties;
    private static InputStream input;

    public static Connection getConnection(){
        properties = new Properties();

        try {

            input = ConnectionFactory.class.getResourceAsStream("/credentials.properties");
            properties.load(input);

        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(
                    properties.getProperty("endpoint"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;

    }

}
