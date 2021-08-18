package com.revature.orm.util;

import java.lang.reflect.Field;
import com.revature.orm.annotations.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ColumnField {
    private Field field;
    private static final Logger logger = LoggerFactory.getLogger(ColumnField.class);
    public ColumnField(Field field){
        if(field.getAnnotation(Column.class) == null){
            logger.warn("Cannot create ColumnField object!" +
                "Provided field, " + getName() + " is not annotated with @Column");

            throw new IllegalStateException("Cannot create ColumnField object!" +
                    "Provided field, " + getName() + " is not annotated with @Column");
        }
        this.field = field;
    }

    public String getName() {return field.getName();}
    public Class<?> getType(){return field.getType();}
    public String getColumnName(){
        return field.getAnnotation(Column.class).columnName();
    }
}
