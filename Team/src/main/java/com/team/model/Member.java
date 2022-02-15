package com.team.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Member {
    private String name;
    private int age;
    private String location;
    @JsonProperty("Role")
    private String role;

    public Member(String name, int age, String location, String role) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.role = role;
    }

    public Member() {
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
        Member member = (Member) o;
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
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", location='").append(location).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
