package com.revature.orm.persistence;

public interface DAO {
    // Simple CRUD methods
    // Create
    public void createTable(Class<?> clazz);
    public void insert(Class<?> clazz, Object o);


    // Read
    public void getById(Object table, int id);
    public void getOneFromTable(Class<?> table, Object item);

    // Update

    // Delete


}
