package com.revature.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class TypeToStringMap {

    private class classData{
        int i;
        double d;
        String s;
        Date date;


        
    }

    public HashMap<Type, String> dataTypeToStringConversion(){
        classData cd = new classData();
        Field[] field = cd.getClass().getDeclaredFields();

        HashMap<Type,String> ans = new HashMap<>();

        for(Field f: field){
            if(f.getType() == Integer.TYPE){
                ans.put(f.getType(), "int");
            }else if(f.getType() == Double.TYPE){
                ans.put(f.getType(), "double precision");
            }else if(f.getType().equals(String.class)){
                ans.put(f.getType(), "varchar (50)");
            }else if(f.getType().equals(Date.class)){
                ans.put(f.getType(), "date");
            }
        }
        return ans;
    }

    public HashMap<String, Type> reversedMapStringToDataType(){
        HashMap<Type, String> normal = dataTypeToStringConversion();
        HashMap<String, Type> reversed = new HashMap<>();

        for(Map.Entry<Type, String> entry: normal.entrySet() ){
            reversed.put(entry.getValue(), entry.getKey());
        }
        return reversed;
    }

}
