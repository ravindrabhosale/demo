package com.team.service;

import com.team.GroupServiceApplication;
import com.team.model.GroupingResult;
import com.team.model.Member;
import com.team.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GroupServiceApplication.class)

public class MemberGroupingServiceTests {

    private static final Logger LOGGER = LogManager.getLogger(MemberGroupingServiceTests.class);

    private static final String DELHI  = "Delhi";
    private static final String PUNE = "Pune";
    private static final String BANGLORE = "Banglore";

    @Autowired
    private MemberGroupingService groupingService;

    @Before
    public void setUp() {
    }

    @Test
    public void testBuildGroup() {
        List<Member> members = buildData();
        GroupingResult result =  groupingService.groupMembers(members);
        LOGGER.info("result group: {}", result.getGroups().size());
        LOGGER.info("result ungroup: {}", result.getUnGroup().size());
        assertEquals("Number of groups ",3,result.getGroups().size());
        assertEquals("Number of ungrouped ",5,result.getUnGroup().size());
    }

    @Test
    public void testOnlyLeaders() {
        List<Member> members = buildDataOnlyLeaders();
        GroupingResult result =  groupingService.groupMembers(members);
        LOGGER.info("result group: {}", result.getGroups().size());
        LOGGER.info("result ungroup: {}", result.getUnGroup().size());
        assertEquals("Number of groups ",0,result.getGroups().size());
        assertEquals("Number of ungrouped ",5,result.getUnGroup().size());
    }

    @Test
    public void testNoData() {
        List<Member> members = buildNoData();
        GroupingResult result =  groupingService.groupMembers(members);
        LOGGER.info("result group: {}", result.getGroups().size());
        LOGGER.info("result ungroup: {}", result.getUnGroup().size());
        assertEquals("Number of groups ",0,result.getGroups().size());
        assertEquals("Number of ungrouped ",0,result.getUnGroup().size());
    }

    @Test
    public void testOnlyLeadersSingleLocation() {
        List<Member> members = buildDataOnlyLeadersSingleLocation();
        GroupingResult result =  groupingService.groupMembers(members);
        LOGGER.info("result group: {}", result.getGroups().size());
        LOGGER.info("result ungroup: {}", result.getUnGroup().size());
        assertEquals("Number of groups ",1,result.getGroups().size());
        assertEquals("Number of ungrouped ",2,result.getUnGroup().size());
    }

    @Test
    public void testMultipleGroupAtLocation() {
        List<Member> members = buildMultipleGroupAtLocation();
        GroupingResult result =  groupingService.groupMembers(members);
        LOGGER.info("result group: {}", result.getGroups().size());
        LOGGER.info("result ungroup: {}", result.getUnGroup().size());
        assertEquals("Number of groups ",2,result.getGroups().size());
        assertEquals("Number of ungrouped ",3,result.getUnGroup().size());
    }

    private static List<Member> buildData(){
        List<Member> members = new ArrayList<>();

        members.add(new Member("Priyesh", 32, BANGLORE, Role.MARKETING.getValue()));
        members.add(new Member("Hitesh", 21, BANGLORE, Role.SALES.getValue()));
        members.add(new Member("Datta", 28, BANGLORE, Role.ENGINEER.getValue()));
        members.add(new Member("Vaibhav", 48, BANGLORE, Role.SALES.getValue()));
        members.add(new Member("Pranali", 28, BANGLORE, Role.MARKETING.getValue()));
        members.add(new Member("Shrikant", 22, BANGLORE, Role.ENGINEER.getValue()));

        members.add(new Member("Amit", 38, DELHI, Role.ENGINEER.getValue()));
        members.add(new Member("Jassi", 32, DELHI, Role.MARKETING.getValue()));
        members.add(new Member("Dela", 29, DELHI, Role.MARKETING.getValue()));
        members.add(new Member("Smita", 31, DELHI, Role.SALES.getValue()));

        members.add(new Member("Pramod", 44, PUNE, Role.MARKETING.getValue()));
        members.add(new Member("Kunal", 44, PUNE, Role.SALES.getValue()));
        members.add(new Member("Eric", 29, PUNE, Role.ENGINEER.getValue()));
        members.add(new Member("Dinesh", 39, PUNE, Role.SALES.getValue()));
        return members;
    }

    private static List<Member> buildDataOnlyLeaders(){
        List<Member> members = new ArrayList<>();
        members.add(new Member("Vaibhav", 48, BANGLORE, Role.SALES.getValue()));

        members.add(new Member("Amit", 38, DELHI, Role.ENGINEER.getValue()));

        members.add(new Member("Pramod", 44, PUNE, Role.MARKETING.getValue()));
        members.add(new Member("Kunal", 44, PUNE, Role.SALES.getValue()));
        members.add(new Member("Dinesh", 39, PUNE, Role.SALES.getValue()));
        return members;
    }

    private static List<Member> buildMultipleGroupAtLocation(){
        List<Member> members = new ArrayList<>();
        members.add(new Member("Vaibhav", 48, BANGLORE, Role.SALES.getValue()));

        members.add(new Member("Amit", 38, DELHI, Role.ENGINEER.getValue()));
        members.add(new Member("Kunal", 28, DELHI, Role.SALES.getValue()));
        members.add(new Member("Dinesh", 23, DELHI, Role.ENGINEER.getValue()));
        members.add(new Member("Vinita", 39, DELHI, Role.SALES.getValue()));
        members.add(new Member("Pankaj", 56, DELHI, Role.MARKETING.getValue()));
        members.add(new Member("Meena", 32, DELHI, Role.ENGINEER.getValue()));
        members.add(new Member("Yogesh", 45, DELHI, Role.MARKETING.getValue()));

        members.add(new Member("Pramod", 44, PUNE, Role.MARKETING.getValue()));
        return members;
    }

    private static List<Member> buildDataOnlyLeadersSingleLocation(){
        List<Member> members = new ArrayList<>();
        members.add(new Member("Vaibhav", 48, BANGLORE, Role.SALES.getValue()));

        members.add(new Member("Arjun", 38, DELHI, Role.ENGINEER.getValue()));

        members.add(new Member("Pramod", 44, PUNE, Role.MARKETING.getValue()));
        members.add(new Member("Kunal", 44, PUNE, Role.ENGINEER.getValue()));
        members.add(new Member("Dinesh", 39, PUNE, Role.SALES.getValue()));
        return members;
    }

    private static List<Member> buildNoData(){
        List<Member> members = new ArrayList<>();
        return members;
    }
}
