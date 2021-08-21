package com.revature.orm.persistence;

import java.util.List;

public interface DAO {
    // Simple CRUD methods
    // Create
    public void createTable(Class<?> clazz);
    public void insert(Class<?> clazz, Object ...o);


    // Read
    public Object getById(Class clazz, int id);
    public List<Object> getAll(Class clazz);

    // Update

    public void update(Class clazz, Object ...o);

    // Delete
    public void removeById(Class clazz, int id);


}
