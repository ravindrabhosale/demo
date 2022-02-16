package com.team.persistence.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    private static final Logger LOGGER = LogManager.getLogger(CascadeSaveMongoEventListener.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        LOGGER.info("onBeforeConvert: {}", event);
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new FieldCascadeCallback(source, mongoOperations));
    }
}