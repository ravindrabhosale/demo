package com.team.persistence.service;

import com.team.model.Group;
import com.team.model.Member;
import com.team.persistence.repository.GroupRepository;
import com.team.persistence.repository.MemberRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MongoService {
    private static final Logger LOGGER = LogManager.getLogger(MongoService.class);

    private MongoTemplate mongoTemplate;

    private GroupRepository groupRepository;

    private MemberRepository memberRepository;

    public MongoService(MongoTemplate mongoTemplate, GroupRepository groupRepository, MemberRepository memberRepository) {
        this.mongoTemplate = mongoTemplate;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }

    public List<List<Member>> getAllGroupMembers() {
        LOGGER.info("Get all groups");
        List<Group> groups = this.groupRepository.findAll();
        List<List<Member>> memberGroups = new ArrayList<>();
        for (Group group : groups) {
            memberGroups.add(group.getMembers());
        }
        return memberGroups;
    }

    public void save(List<List<Member>> memberGroups, List<Member> members) {
        LOGGER.info("Save the members and groups");
        for (Member member : members) {
            memberRepository.save(member);
        }
        for (List<Member> groupMembers : memberGroups) {
            Group group = new Group();
            UUID uuid = UUID.randomUUID();
            group.setId(uuid.toString());
            group.setMembers(groupMembers);
            groupRepository.save(group);
        }
        LOGGER.info("Save finished");
    }
}
