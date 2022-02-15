package com.team.model;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    List<List<Member>> groups = new ArrayList<>();

    List<Member> ungrouped = new ArrayList<>();

    public List<List<Member>> getGroups() {
        return groups;
    }

    public void add(List<Member> members) {
        this.groups.add(members);
    }

    public List<Member> getUngrouped() {
        return ungrouped;
    }

    public void setUngrouped(List<Member> ungrouped) {
        this.ungrouped = ungrouped;
    }

    public void addUngrouped(Member member) {
        this.ungrouped.add(member);
    }

    public void addAllUngrouped(List<Member> members) {
        this.ungrouped.addAll(members);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Partition{");
        sb.append("teams=").append(groups);
        sb.append(", ungrouped=").append(ungrouped);
        sb.append('}');
        return sb.toString();
    }
}
