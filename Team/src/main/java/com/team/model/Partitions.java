package com.team.model;

import java.util.*;

public class Partitions {
    Map<String, Partition> partitions = new HashMap<>();

    public Collection<Partition> getPartitions() {
        return partitions.values();
    }

    public void addMember(String location, Member member) {
        this.partitions.putIfAbsent(location, new Partition());
        this.partitions.get(location).addUngrouped(member);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Partitions{");
        sb.append("partitions=").append(partitions);
        sb.append('}');
        return sb.toString();
    }
}
