package com.team.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GroupingResult {
    private List<List<Member>> groups = new ArrayList<>();
    @JsonProperty("un-groups")
    private List<Member> unGroup = new ArrayList<>();

    public List<List<Member>> getGroups() {
        return groups;
    }

    public void setGroups(List<List<Member>> groups) {
        this.groups = groups;
    }

    public void addTeams(List<List<Member>> groups) {
        this.groups.addAll(groups);
    }

    public List<Member> getUnGroup() {
        return unGroup;
    }

    public void setUnGroup(List<Member> unGroup) {
        this.unGroup = unGroup;
    }

    public void addAllUnGroup(List<Member> unGroup) {
        this.unGroup.addAll(unGroup);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupingResult{");
        sb.append("teams=").append(groups);
        sb.append(", unGroup=").append(unGroup);
        sb.append('}');
        return sb.toString();
    }
}
