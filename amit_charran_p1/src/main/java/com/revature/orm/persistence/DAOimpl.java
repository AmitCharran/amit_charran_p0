package com.revature.orm.persistence;

import com.revature.orm.annotations.Id;
import com.revature.orm.ormDriver;
import com.revature.orm.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

public class DAOimpl implements DAO{

    final static Logger logger = LoggerFactory.getLogger(DAOimpl.class);


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
                + " ADD COLUMN " + id.getName() + " " + mapTypeToSQLType.get(id.getType()) +
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
    public void insert(Class<?> clazz, Object ...o) {
        if(!tableExists(clazz.getSimpleName().toLowerCase(Locale.ROOT))){
            throw new IllegalStateException("table does not exists, table name: " + clazz.getClass().getSimpleName());
        }

        Metamodel mm = new Metamodel(clazz);
        List<ColumnField> fields = mm.getColumns();
        Object[] allObjects = o;



        List<String> columnNames = getColumnNames(clazz).get();
        List<String> columnTypes = getAllColumnTypes(clazz).get();

        if(allObjects.length != columnNames.size() - 1){
            // throw exception
            System.out.println("Parameters do not match");
            return;
        }

        HashMap<String,Type> reversedMap = new TypeToStringMap().reversedMapStringToDataType();

        String insertString = "INSERT INTO " + mm.getSimpleClassName() + " (";
        String columnNamesFormat = "";
        for(int i = 0; i < columnNames.size(); i++){
            String s = columnNames.get(i);
            if(i == 0){
                continue;
            }else if(i == columnNames.size() - 1){
                columnNamesFormat +=  s + ")";
            }else{
                columnNamesFormat += s +", ";
            }
        }

        insertString += columnNamesFormat + " values (";


        columnNamesFormat = "";
        for(int i =0; i < allObjects.length;i++){
            if(i == allObjects.length -1){
                columnNamesFormat += "\'" +  allObjects[i] + "\')";
            }else{
                columnNamesFormat += "\'" + allObjects[i] + "\', ";
            }
        }
        insertString += columnNamesFormat;
        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            s.execute(insertString);


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getById(Class clazz, int id) {
        if(!hasNoArgConstructor(clazz)){
            logger.warn("Class does not have no Arg constructor. Cannot continue" +
                    "\nclass name: " + clazz.getSimpleName());
            return null;
        }
        Metamodel mm = new Metamodel(clazz);


        List<String> allColumnNames = getColumnNames(clazz).get();
        List<Method> unsortedSet= mm.getSetMethodsUnsorted();

        List<Method> sortedSet = sortUnsortedSet(allColumnNames, unsortedSet);

        ResultSet rs;
        Statement s;
        String selectAll = "SELECT * FROM " + mm.getSimpleClassName() + " WHERE " + allColumnNames.get(0) + " = " + id;
        Object o = null;

        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            rs = s.executeQuery(selectAll);



            while(rs.next()){
                o = clazz.newInstance();
                for(int i = 0; i < allColumnNames.size(); i++) {
                    // make sure type is correct
                    Object oToSet = rs.getObject(allColumnNames.get(i));
                    sortedSet.get(i).invoke(o, oToSet);
                    // set object and make sure it is the right type
                }
            }

        }catch (SQLException e){
            logger.warn("Cannot create object instance from table " + mm.getSimpleClassName());
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;

    }

    @Override
    public List<Object> getAll(Class clazz) {
        if(!hasNoArgConstructor(clazz)){
            logger.warn("Class does not have no Arg constructor. Cannot continue" +
                    "\nclass name: " + clazz.getSimpleName());
            return null;
        }

        Metamodel mm = new Metamodel(clazz);

        String selectAll = "SELECT * FROM " + mm.getSimpleClassName();
        List<String> allColumnNames = getColumnNames(clazz).get();
        List<Method> unsortedSet= mm.getSetMethodsUnsorted();

        List<Method> sortedSet = sortUnsortedSet(allColumnNames, unsortedSet);

        ResultSet rs;
        Statement s;
        ArrayList<Object> allObject = new ArrayList<>();

        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            rs = s.executeQuery(selectAll);

            Object o = null;

            while(rs.next()){
                o = clazz.newInstance();
                for(int i = 0; i < allColumnNames.size(); i++) {
                    // make sure type is correct
                    Object oToSet = rs.getObject(allColumnNames.get(i));
                    sortedSet.get(i).invoke(o, oToSet);
                    // set object and make sure it is the right type
                }
                allObject.add(o);
            }

        }catch (SQLException e){
            logger.warn("Cannot create object instance from table " + mm.getSimpleClassName());
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return allObject;
    }

    @Override
    public void removeById(Class clazz, int id) {
        Metamodel mm = new Metamodel(clazz);
        List<String> columnNames = getColumnNames(clazz).get();

        String delete = "DELETE FROM " + mm.getSimpleClassName()
                + " WHERE " + columnNames.get(0) + " = " + id;

        Statement s;
        try(Connection connection = ConnectionUtil.getConnection(url,user,pass)){
            s = connection.createStatement();
            s.execute(delete);

        }catch (SQLException e){
            logger.warn("Cannot Delete " + id + " from table " + mm.getSimpleClassName());
        }


    }

    private List<Method> sortUnsortedSet(List<String> allColumnNames, List<Method> unsortedSet) {
        List<Method> sortedSet = new ArrayList<>();
        for(int i =0; i < allColumnNames.size(); i++){
            String colName = allColumnNames.get(i);
            for(int j = 0; j < unsortedSet.size(); j++){
                String methName= unsortedSet.get(j).getName().toLowerCase(Locale.ROOT);
                if(methName.contains(colName)){
                    sortedSet.add(unsortedSet.get(j));
                    break;
                }
            }
        }
        return sortedSet;
    }

    private boolean hasNoArgConstructor(Class<?> clazz) {
        return Stream.of(clazz.getConstructors())
                .anyMatch((c) -> c.getParameterCount() == 0);
    }


}
