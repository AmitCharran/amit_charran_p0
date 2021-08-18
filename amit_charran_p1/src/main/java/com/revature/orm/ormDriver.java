package com.revature.orm;

import com.revature.orm.model.User;
import com.revature.orm.util.ColumnField;
import com.revature.orm.util.Configuration;
import com.revature.orm.util.Metamodel;
import com.revature.orm.util.TypeToStringMap;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ormDriver {
    private static Properties properties;
    private static InputStream input;
    public static void main(String[] args) {

        try {

            input = ormDriver.class.getResourceAsStream("/credentials.properties");
            properties.load(input);

        }catch (IOException e){
            e.printStackTrace();
        }
        Configuration cfg = new Configuration(
                properties.getProperty("endpoint"),
                properties.getProperty("username"),
                properties.getProperty("password"));





    }
}
