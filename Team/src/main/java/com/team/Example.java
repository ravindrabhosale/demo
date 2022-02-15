package com.team;

import com.team.model.*;
import com.team.service.PartitionProcessor;
import com.team.service.GroupBuilder;

import java.util.ArrayList;
import java.util.List;

public class Example {
    private static final String DELHI  = "Delhi";
    private static final String PUNE = "Pune";
    private static final String BANGLORE = "Banglore";

    public static void main(String[] args) {
        List<Member> members = buildNoData();
        GroupBuilder groupBuilder = new GroupBuilder();
        PartitionProcessor partitionProcessor = new PartitionProcessor();
        Partitions partitions = partitionProcessor.process(members);
        GroupingResult result = new GroupingResult();
        for (Partition partition :partitions.getPartitions() ) {
            groupBuilder.buildGroups(partition);
            result.addTeams(partition.getGroups());
            result.addAllUnGroup(partition.getUngrouped());
        }
        System.out.println("Finished: "+result);
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

    private static List<Member> buildDataOnlyLeadersSingleLocation(){
        List<Member> members = new ArrayList<>();
        members.add(new Member("Vaibhav", 48, BANGLORE, Role.SALES.getValue()));

        members.add(new Member("Amit", 38, DELHI, Role.ENGINEER.getValue()));

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