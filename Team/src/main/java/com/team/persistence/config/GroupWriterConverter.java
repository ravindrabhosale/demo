package com.team.persistence.config;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.team.model.Group;
import com.team.model.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupWriterConverter implements Converter<Group, DBObject> {

    private static final Logger LOGGER = LogManager.getLogger(GroupWriterConverter.class);

    @Override
    public DBObject convert(final Group group) {
        LOGGER.info("GroupWriterConverter: {}", group);
        final DBObject dbObject = new BasicDBObject();

        if (group.getMembers() != null) {
            List<DBObject> dbObjects = new ArrayList<>();
            for (Member member: group.getMembers()) {
                final DBObject memberDbObject = new BasicDBObject();
                memberDbObject.put("name", member.getName());
                memberDbObject.put("location", member.getLocation());
                memberDbObject.put("role", member.getRole());
                memberDbObject.put("age", member.getAge());

                dbObjects.add(memberDbObject);
            }
            dbObject.put("members", dbObjects);
        }
        dbObject.removeField("_class");
        return dbObject;
    }

    @Override
    public <U> Converter<Group, U> andThen(Converter<? super DBObject, ? extends U> after) {
        return null;
    }
}
