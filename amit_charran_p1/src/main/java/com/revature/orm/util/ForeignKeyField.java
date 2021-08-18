package com.revature.orm.util;

import com.revature.orm.annotations.Id;
import com.revature.orm.annotations.JoinColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ForeignKeyField {

    private Field field;
    private static final Logger logger = LoggerFactory.getLogger(ForeignKeyField.class);

    public ForeignKeyField(Field field) {
        if (field.getAnnotation(JoinColumn.class) == null) {
            logger.warn("Cannot create ForeignKeyField object! Provided field, " + getName() + "is not annotated with @JoinColumn");
            throw new IllegalStateException("Cannot create ForeignKeyField object! Provided field, " + getName() + "is not annotated with @JoinColumn");
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
        return field.getAnnotation(JoinColumn.class).columnName();
    }

}
