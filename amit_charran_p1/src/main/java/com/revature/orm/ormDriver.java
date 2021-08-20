package com.revature.orm;

import com.revature.orm.model.Test;
import com.revature.orm.model.User;
import com.revature.orm.persistence.DAOimpl;
import com.revature.orm.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ormDriver {
    private static Properties properties;
    private static InputStream input;

    final static Logger logger = LoggerFactory.getLogger(ormDriver.class);

    public static void main(String[] args) {

        properties = new Properties();
        try {
            Class.forName("org.postgresql.Driver");
            input = ormDriver.class.getResourceAsStream("/credentials.properties");
            properties.load(input);

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        Configuration cfg = new Configuration(
                properties.getProperty("endpoint"),
                properties.getProperty("username"),
                properties.getProperty("password"));
        cfg.setSchema("p0");
       // cfg.addAnnotatedClass(Test.class);

        // now i'm testing for inserting into a table
        // Test a = new Test("one", "two", 3);
        cfg.insertIntoTable(Test.class, "test3", "test4", 3, 5.34, new Date(System.currentTimeMillis()));
        ArrayList<Object> al = (ArrayList<Object>) cfg.getAll(Test.class);
        Object o = (Object) cfg.getById(Test.class, 7);

        //cfg.deleteByID(Test.class, 2);
        if(o instanceof Test){
            Test x = (Test) o;
            System.out.println(x.getTestField5());
        }

        if(al.get(2) instanceof Test){
            Test x = (Test) al.get(5);
            System.out.println(x.getTestField5());
        }

    }
}
