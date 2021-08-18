package com.revature.orm.persistence;

import com.revature.orm.annotations.Id;
import com.revature.orm.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DAOimpl implements DAO{

    public static Logger logger = Logger.getLogger(DAOimpl.class);
    private String url;
    private String user;
    private String pass;


    public DAOimpl(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void createTable(Class<?> clazz) {
        Metamodel mm = new Metamodel(clazz);
        if(tableExists(clazz.getSimpleName())){
            logger.info("Table name " + clazz.getSimpleName() + " already exists");
            return;
        }

        createTable(mm);
        List<ColumnField> columns = mm.getColumns();
        for(ColumnField c: columns){
            addColumn(mm,c);
        }
    }
    private void addColumn(Metamodel mm, ColumnField c){

        HashMap<Type, String> mapTypeToSQLType = new TypeToStringMap().dataTypeToStringConversion();

        String addColumn = "ALTER TABLE " + mm.getSimpleClassName()
                            + "ADD COLUMN " + c.getName() + " " + mapTypeToSQLType.get(c.getType());

        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url, user, pass)){
            s = connection.createStatement();
            s.executeQuery(addColumn);
        }catch (SQLException e){
            logger.warn(e.getMessage());
        }

    }

    private void createTable(Metamodel mm){
        String createTable = "CREATE TABLE " + mm.getSimpleClassName() + "()";
        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            s.executeQuery(createTable);
        }catch (SQLException e){
            logger.warn("cannot create table even though table does not exist");
        }
    }

    private boolean tableExists(String tableName){
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)) {
            DatabaseMetaData dbm = connection.getMetaData();

            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        logger.info("Cannot connect to DB, so will print table already exists");
        return true; // do not want to create table
    }

    @Override
    public void insert(Class<?> clazz, Object o) {

    }

    @Override
    public void getById(Object table, int id) {

    }

    @Override
    public void getOneFromTable(Class<?> table, Object item) {

    }
}
