package com.team.service;

import com.team.model.Member;
import com.team.model.Partitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartitionProcessor {
    private static final Logger LOGGER = LogManager.getLogger(PartitionProcessor.class);

    /**
     * Partition the given payload as per location of members.
     * Each partition can be processed parallel.
     * @param members
     * @return
     */
    public Partitions process(List<Member> members){
        Partitions partitions = new Partitions();
        for (Member member : members) {
            partitions.addMember(member.getLocation(), member);
        }
        LOGGER.info("Generated Partitions:{} ", partitions);
        return partitions;
    }
}
