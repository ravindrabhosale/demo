package com.team.service;

import com.team.controller.GroupController;
import com.team.model.Member;
import com.team.model.Partition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupBuilder {
    private static final Logger LOGGER = LogManager.getLogger(GroupController.class);

    public void buildGroups(Partition partition) {
        LOGGER.info("Building team");
        List<Member>[][] memberMatrix = buildAgeRoleMatrix(partition.getUngrouped());
        List<Member> unGroupedMembers = new ArrayList<>();
        /*              ENGINEER    SALES   MARKETING
         * Age>35       4           0       5
         * Age<=35      7           5       2
         */
        int leaderRowIndex = 0;
        for (int ageColIndex = 0; ageColIndex < memberMatrix[0].length; ) {
            LOGGER.debug("ageColIndex: {}", ageColIndex);
            List<Member> leaders = memberMatrix[leaderRowIndex][ageColIndex];

            if (!leaders.isEmpty()) {
                Member leader = leaders.remove(0);
                LOGGER.info("Leader:{} ", leader);
                List<Member> group = new ArrayList<>();
                group.add(leader);
                for (int roleColIndex = 0; roleColIndex < memberMatrix[0].length; roleColIndex++) {
                    Member teamMember = null;
                    LOGGER.debug("roleColIndex: {}", roleColIndex);
                    if (ageColIndex == roleColIndex) {
                        continue;
                    }
                    for (int ageRowInverseIndex = memberMatrix.length - 1; ageRowInverseIndex >= 0; ageRowInverseIndex--) {
                        if (!memberMatrix[ageRowInverseIndex][roleColIndex].isEmpty()) {
                            teamMember = memberMatrix[ageRowInverseIndex][roleColIndex].remove(0);
                            LOGGER.info("Team Member: {}", teamMember);
                            break;
                        }
                    }

                    if (teamMember != null) {
                        group.add(teamMember);
                    }
                }
                LOGGER.info("Team: {}", group);
                if (group.size() == 3) {
                    partition.add(group);
                } else {
                    unGroupedMembers.addAll(group);
                }
            } else {
                ageColIndex++;
            }
        }
        LOGGER.debug("Finding un grouped: ");
        for (int roleColIndex = 0; roleColIndex < memberMatrix.length; roleColIndex++) {
            for (int ageColIndex = 0; ageColIndex < memberMatrix[roleColIndex].length; ageColIndex++) {
                if (!memberMatrix[roleColIndex][ageColIndex].isEmpty()) {
                    LOGGER.debug("Adding un grouped: {} ", memberMatrix[roleColIndex][ageColIndex]);
                    unGroupedMembers.addAll(memberMatrix[roleColIndex][ageColIndex]);
                }
            }
        }
        LOGGER.info("Adding un grouped:{} ", unGroupedMembers);
        partition.setUngrouped(unGroupedMembers);
        LOGGER.info("Finished partition:{} ", partition);
    }

    /**
     * Build matrix of Role v/s age group.
     * each cell will have list of members for given role and age group
     *              ENGINEER    SALES   MARKETING
     * Age>35       4           0       5
     * Age<=35      7           5       2
     *
     * @param members
     * @return
     */
    private List<Member>[][] buildAgeRoleMatrix(List<Member> members) {
        int noOfRoles = 3;
        int ageGroups = 2;
        List<Member>[][] memberMatrix = new List[ageGroups][noOfRoles];

        for (int ageIndex = 0; ageIndex < ageGroups; ageIndex++) {
            for (int roleIndex = 0; roleIndex < noOfRoles; roleIndex++) {
                memberMatrix[ageIndex][roleIndex] = new ArrayList<>();
            }
        }

        for (Member member : members) {
            int roleIndex = getRoleIndex(member);
            int ageIndex = getAgeIndex(member);
            memberMatrix[ageIndex][roleIndex].add(member);
        }
        for (int ageIndex = 0; ageIndex < ageGroups; ageIndex++) {
            for (int roleIndex = 0; roleIndex < noOfRoles; roleIndex++) {
                LOGGER.info("Partition Matrix members: {}", memberMatrix[ageIndex][roleIndex]);
            }
        }
        return memberMatrix;
    }

    /**
     * Get the index for role of matrix for member
     * @param member
     * @return
     */
    private int getRoleIndex(Member member) {
        int roleIndex = 0;// Initialize default to Engineer
        LOGGER.info("Member role:{} ", member.getRole());
        switch (member.getRole()) {
            case "Engineer":
                roleIndex = 0;
                break;
            case "Sales":
                roleIndex = 1;
                break;
            case "Marketing":
                roleIndex = 2;
                break;
        }
        return roleIndex;
    }

    /**
     * Get the index for age of matrix from member
     * @param member
     * @return
     */
    private int getAgeIndex(Member member) {
        int ageIndex = 0; // Initialize default to age > 35
        if (member.getAge() > 35) {
            ageIndex = 0;
        } else {
            ageIndex = 1;
        }
        return ageIndex;
    }
}
