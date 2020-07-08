package com.github.jengo.threaddp.Balking.A4_4b;

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
