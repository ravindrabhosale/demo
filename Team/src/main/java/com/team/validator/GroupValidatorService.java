package com.team.validator;

import com.team.controller.GroupController;
import com.team.exception.GroupException;
import com.team.model.Member;
import com.team.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GroupValidatorService {
    private static final Logger LOGGER = LogManager.getLogger(GroupController.class);

    public void validate(List<Member> members) throws GroupException {
        if (CollectionUtils.isEmpty(members)) {
            throw new GroupException("The group members can not be NULL or empty");
        }

        for (Member member : members) {
            validateName(member);
            validateAge(member);
            validateRole(member);
            validateLocation(member);
        }
    }


    /**
     * validate member name should not be null or empty
     *
     * @param member
     */
    private void validateName(Member member) throws GroupException {
        if (member.getName() == null || "".equals(member.getName())) {
            throw new GroupException("The group member name can not be NULL or empty");
        }
    }

    /**
     * validate member age should not be less than 18
     *
     * @param member
     */
    private void validateAge(Member member) throws GroupException {
        if (member.getAge() < 18) {
            throw new GroupException("The group member " + member.getName() + ", age " + member.getAge() + " is less than 18");
        }
    }

    /**
     * Validate member Role, either of Engineer, Marketing, Sales
     *
     * @param member
     * @throws GroupException
     */
    private void validateRole(Member member) throws GroupException {
        if (!Role.ENGINEER.getValue().equals(member.getRole()) &&
                !Role.MARKETING.getValue().equals(member.getRole()) &&
                !Role.SALES.getValue().equals(member.getRole())) {
            throw new GroupException("Unsupported role " + member.getRole() + " for member " + member.getName());
        }
    }

    /**
     * Validate member location for null or empty
     *
     * @param member
     * @throws GroupException
     */
    private void validateLocation(Member member) throws GroupException {
        if (member.getLocation() == null || "".equals(member.getLocation())) {
            throw new GroupException("The group member location can not be NULL or empty");
        }
    }
}
