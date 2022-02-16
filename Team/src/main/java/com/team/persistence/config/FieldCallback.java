package com.team.persistence.config;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class FieldCallback implements ReflectionUtils.FieldCallback {
    
    private boolean idFound;

    @Override
    public void doWith(final Field field) throws IllegalArgumentException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }

    public boolean isIdFound() {
        return idFound;
    }
}