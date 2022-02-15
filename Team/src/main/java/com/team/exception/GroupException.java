package com.team.exception;

public class GroupException extends Exception{

    public GroupException(String message) {
        super(message);
    }

    public GroupException(String message, Throwable t) {
        super(message, t);
    }

    public GroupException(Throwable t) {
        super(t);
    }

}
