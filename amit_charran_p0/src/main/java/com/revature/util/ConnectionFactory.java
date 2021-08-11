package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This file is used to connect to our data base.
 * It utilizes a properties file to access out database.
 */
public class ConnectionFactory {

    private static Connection connection;
    private static Properties properties;
    private static InputStream input;

    /**
     * provides a way for us to connect to our database without tediously rewriting code
     * @return connection with credentials to access the database
     */
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
            connection.setSchema("p0");
        }catch (SQLException e){
            e.printStackTrace();
        }

        return connection;

    }

}
