package com.team.persistence.config;

import com.team.model.Group;
import com.team.model.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;


public class GroupFieldCascadeUpdateMongoEventListener extends AbstractMongoEventListener<Object> {

    private static final Logger LOGGER = LogManager.getLogger(GroupFieldCascadeUpdateMongoEventListener.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        LOGGER.info("onBeforeConvert:{}", event);
        final Object source = event.getSource();
        if ((source instanceof Group) && (((Group) source).getMembers() != null)) {
            for (Member member : ((Group) source).getMembers()) {
                mongoOperations.save(member);
            }
        }
    }
}