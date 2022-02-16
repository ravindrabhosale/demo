package com.team.model;

import java.util.ArrayList;
import java.util.List;

import com.team.persistence.config.FieldCascadeUpdate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "groups")
public class Group {
    @Id
    private String id;

    @DBRef
    @Field("members")
    @FieldCascadeUpdate
    private List<Member> members = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
