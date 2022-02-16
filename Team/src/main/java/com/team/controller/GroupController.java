package com.team.controller;

import com.team.exception.GroupException;
import com.team.persistence.service.MongoService;
import com.team.validator.GroupValidatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.team.model.GroupingResult;
import com.team.model.Member;
import com.team.service.MemberGroupingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class GroupController {
    private static final Logger LOGGER = LogManager.getLogger(GroupController.class);

    private MemberGroupingService groupingService;

    private GroupValidatorService validatorService;

    private MongoService mongoService;

    public GroupController(MemberGroupingService groupingService, GroupValidatorService validatorService, MongoService mongoService) {
        this.groupingService = groupingService;
        this.validatorService = validatorService;
        this.mongoService = mongoService;
    }

    @PostMapping(path = "/ef/groups", consumes = "application/json", produces = "application/json")
    public GroupingResult grouping(@RequestBody List<Member> members) throws GroupException {
        LOGGER.info("Invoked post grouping");
        GroupingResult result = null;
        try {
            validatorService.validate(members);
            result = groupingService.groupMembers(members);
            this.mongoService.save(result.getGroups(), result.getUnGroup());
        } catch (GroupException e) {
            LOGGER.error("Failed to process grouping", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Failed to process grouping", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Failed to process request", e);
        }
        return result;
    }


    @GetMapping(path = "/ef/groups", consumes = "application/json", produces = "application/json")
    public GroupingResult grouping() throws GroupException {
        LOGGER.info("Invoked get grouping");
        GroupingResult result = new GroupingResult();
        result.setGroups(this.mongoService.getAllGroupMembers());
        return result;
    }
}
