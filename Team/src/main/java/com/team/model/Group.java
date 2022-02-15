package com.team.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.add( member);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Team{");
        sb.append("members=").append(members);
        sb.append('}');
        return sb.toString();
    }
}
