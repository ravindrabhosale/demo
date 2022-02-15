package com.team.service;

import com.team.model.GroupingResult;
import com.team.model.Member;
import com.team.model.Partition;
import com.team.model.Partitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberGroupingService {
    private static final Logger LOGGER = LogManager.getLogger(MemberGroupingService.class);

    private GroupBuilder groupBuilder;

    private PartitionProcessor partitionProcessor;

    public MemberGroupingService(GroupBuilder groupBuilder, PartitionProcessor partitionProcessor) {
        this.groupBuilder = groupBuilder;
        this.partitionProcessor = partitionProcessor;
    }

    public GroupingResult groupMembers(List<Member> members) {
        Partitions partitions = partitionProcessor.process(members);
        GroupingResult result = new GroupingResult();
        for (Partition partition : partitions.getPartitions()) {
            groupBuilder.buildGroups(partition);
            result.addTeams(partition.getGroups());
            result.addAllUnGroup(partition.getUngrouped());
        }
        return result;
    }
}
