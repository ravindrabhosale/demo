package com.team.persistence.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "members")
public class MemberDto {
    @Id
    private String name;
    private int age;
    private String location;
    private String role;
    private String groupId;

    public MemberDto(String name, int age, String location, String role) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.role = role;
    }

    public MemberDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto member = (MemberDto) o;
        return getAge() == member.getAge() &&
                getName().equals(member.getName()) &&
                getLocation().equals(member.getLocation()) &&
                getRole().equals(member.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getLocation(), getRole());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MemberDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", location='").append(location).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
