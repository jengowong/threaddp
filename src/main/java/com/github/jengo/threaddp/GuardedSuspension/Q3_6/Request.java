package com.github.jengo.threaddp.GuardedSuspension.Q3_6;

public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "[ Request " + name + " ]";
    }
}
