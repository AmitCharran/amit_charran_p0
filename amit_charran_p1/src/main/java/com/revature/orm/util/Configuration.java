package com.revature.orm.util;

import com.revature.orm.ormDriver;
import com.revature.orm.persistence.DAO;
import com.revature.orm.persistence.DAOimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Configuration {
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private List<Metamodel<Class<?>>> metamodelList;
    private DAOimpl dao;

    final static Logger logger = LoggerFactory.getLogger(Configuration.class);

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
        dao = new DAOimpl(dbURL, dbUsername, dbPassword);
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

        dao = new DAOimpl(dbURL,dbUsername,dbPassword);
        dao.createTable(annotatedClass);

        return this; // we're returning the
    }

    public List<Object> getAll(Class clazz){

        if(!dao.tableExists(clazz.getSimpleName().toLowerCase())){
               logger.warn("Table name " + clazz.getSimpleName().toLowerCase() + " does not exist. Cannot retrieve");
        }

        return dao.getAll(clazz);
    }

    public Object getById(Class clazz, int id){
        return dao.getById(clazz, id);
    }


    public void insertIntoTable(Class clazz, Object ...o){
       dao.insert(clazz, o);
    }


    public void update(Class clazz, Object ...o){
        dao.update(clazz, o);
    }

    public void deleteByID(Class clazz,int id){
        dao.removeById(clazz, id);
    }

    public List<String> getColumnNames(Class clazz){
        return dao.getColumnNames(clazz).orElse(null);
    }

    public List<String> getColumnTypes(Class clazz){
        return dao.getAllColumnTypes(clazz).orElse(null);
    }

    public List<Metamodel<Class<?>>> getMetamodels() {
        return (metamodelList == null) ? Collections.emptyList() : metamodelList;
    }

    public void setSchema(String schema){
        ConnectionUtil.setSchema(schema);
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
