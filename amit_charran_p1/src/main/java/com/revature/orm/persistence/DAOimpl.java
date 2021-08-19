package com.revature.orm.persistence;

import com.revature.orm.annotations.Id;
import com.revature.orm.ormDriver;
import com.revature.orm.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

public class DAOimpl implements DAO{



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
        if(tableExists(mm.getSimpleClassName())){
            throw new IllegalStateException("Table name " + mm.getSimpleClassName() + " already exists");
        }

        createTable(mm);
        IdField id = mm.getPrimaryKey();
        addPrimaryKey(mm, id);
        List<ColumnField> columns = mm.getColumns();
        for(ColumnField c: columns){
            addColumn(mm,c);
        }
    }
    private void addPrimaryKey(Metamodel mm, IdField id){
        HashMap<Type, String> mapTypeToSQLType = new TypeToStringMap().dataTypeToStringConversion();
        String addKey = "ALTER TABLE " + mm.getSimpleClassName()
                + " ADD COLUMN " + id.getColumnName() + " " + mapTypeToSQLType.get(id.getType()) +
                " GENERATED ALWAYS AS IDENTITY";

        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            s.execute(addKey);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Optional<List<String>> getColumnNames(Class clazz){
        List<String> ans = new ArrayList<>();
        Metamodel mm = new Metamodel(clazz);
        if(!tableExists(mm.getSimpleClassName())){
            throw new IllegalStateException("Table does not exist");
        }
        String getAllFromTable = "SELECT * FROM " + mm.getSimpleClassName();
        PreparedStatement ps;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
        ps = connection.prepareStatement(getAllFromTable);
        ResultSetMetaData meta = ps.getMetaData();
        for(int i =1; i <= meta.getColumnCount(); i++){
            ans.add(meta.getColumnName(i));
        }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.of(ans);
    }

    public Optional<List<String>> getAllColumnTypes(Class clazz){
        List<String> ans = new ArrayList<>();
        Metamodel mm = new Metamodel(clazz);
        if(!tableExists(mm.getSimpleClassName())){
            throw new IllegalStateException("Table does not exist");
        }
        String getAllFromTable = "SELECT * FROM " + mm.getSimpleClassName();
        PreparedStatement ps;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            ps = connection.prepareStatement(getAllFromTable);
            ResultSetMetaData meta = ps.getMetaData();
            for(int i =1; i <= meta.getColumnCount(); i++){
                ans.add(meta.getColumnTypeName(i));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.of(ans);
    }

    private void addColumn(Metamodel mm, ColumnField c){

        HashMap<Type, String> mapTypeToSQLType = new TypeToStringMap().dataTypeToStringConversion();

        String addColumn = "ALTER TABLE " + mm.getSimpleClassName()
                            + " ADD COLUMN " + c.getName() + " " + mapTypeToSQLType.get(c.getType());

        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url, user, pass)){
            s = connection.createStatement();
            s.execute(addColumn);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void createTable(Metamodel mm){
        String createTable = "CREATE TABLE " + mm.getSimpleClassName() + "()";
        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            s.execute(createTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean tableExists(String tableName){
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

//        logger.info("Cannot connect to DB, so will print table already exists");
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
