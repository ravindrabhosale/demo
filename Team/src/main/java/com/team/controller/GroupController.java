package com.team.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.team.model.GroupingResult;
import com.team.model.Member;
import com.team.service.MemberGroupingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    private static final Logger LOGGER = LogManager.getLogger(GroupController.class);

    private MemberGroupingService groupingService;

    public GroupController(MemberGroupingService groupingService) {
        this.groupingService = groupingService;
    }

    @PostMapping(path="/ef/groups", consumes = "application/json", produces = "application/json")
    public GroupingResult grouping(@RequestBody List<Member> members) {
        LOGGER.info("Invoked grouping");
        return groupingService.groupMembers(members);
    }
}
