package com.team.model;

public enum Role {
    ENGINEER("Engineer"),
    SALES("Sales"),
    MARKETING("Marketing");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
