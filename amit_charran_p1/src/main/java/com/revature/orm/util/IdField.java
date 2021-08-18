package com.revature.orm.util;

import com.revature.orm.annotations.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class IdField {
    private Field field;
    private static final Logger logger = LoggerFactory.getLogger(IdField.class);
    public IdField(Field field){
        if(field.getAnnotation(Id.class) == null){
            logger.warn("Cannot create IdField object! Provided field, "
                    + getName() + "is not annotated with @Id");
            throw new IllegalStateException("Cannot create IdField object! Provided field, "
                    + getName() + "is not annotated with @Id");

        }
        this.field = field;
    }
    public String getName() {
        return field.getName();
    }
    public Class<?> getType() {
        return field.getType();
    }
    public String getColumnName() {
        return field.getAnnotation(Id.class).columnName();
    }

}
