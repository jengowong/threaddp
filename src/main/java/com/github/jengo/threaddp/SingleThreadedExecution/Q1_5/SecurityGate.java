package com.github.jengo.threaddp.SingleThreadedExecution.Q1_5;

public class SecurityGate {
    private int counter = 0;
    public void enter() {
        counter++;
    }
    public void exit() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }
}
