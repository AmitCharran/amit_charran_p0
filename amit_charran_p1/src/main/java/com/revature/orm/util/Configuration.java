package com.revature.orm.util;

import com.revature.orm.persistence.DAO;
import com.revature.orm.persistence.DAOimpl;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Configuration {
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private List<Metamodel<Class<?>>> metamodelList;
    private DAO dao;

    /**
     * No Args Constructor
     */
    public Configuration(){
        dbURL = "";
        dbUsername = "";
        dbPassword = "";
        dao = new DAOimpl(dbURL, dbUsername, dbPassword);
    }

    /**
     * Constructor which initializes the
     * @param url endpoint for DB
     * @param user username for DB
     * @param pass password for DB
     */
    public Configuration(String url, String user, String pass){
        dbURL = url;
        dbUsername = user;
        dbPassword = pass;
    }

    /**
     * Structures class based on annotation
     * @param annotatedClass
     * @return
     */
    public Configuration addAnnotatedClass(Class annotatedClass) {
        if(metamodelList == null) {
            metamodelList = new LinkedList<>();
        }

        // generate a method in metamodel that transforms a class into an appropriate data model to be
        // transposed into a relation db object
        metamodelList.add(Metamodel.of(annotatedClass));

        // here we are going to create the table if it does not exist

        return this; // we're returning the
    }

    public List<Metamodel<Class<?>>> getMetamodels() {
        return (metamodelList == null) ? Collections.emptyList() : metamodelList;
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
