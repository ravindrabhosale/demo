package com.team.persistence.config;


import java.lang.reflect.Field;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

public class FieldCascadeCallback implements ReflectionUtils.FieldCallback {

    private static final Logger LOGGER = LogManager.getLogger(FieldCascadeCallback.class);

    private Object source;
    private MongoOperations mongoOperations;

    FieldCascadeCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(FieldCascadeUpdate.class)) {
            final Object fieldValue = field.get(this.source);

            if (fieldValue != null) {
                final FieldCallback callback = new FieldCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                if (List.class.isInstance(fieldValue)) {
                    List list = (List) fieldValue;
                    for (Object obj : list) {
                        this.mongoOperations.save(obj);
                    }
                } else {
                    this.mongoOperations.save(fieldValue);
                }
            }
        }
    }
}