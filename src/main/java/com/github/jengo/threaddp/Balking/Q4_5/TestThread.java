package com.github.jengo.threaddp.Balking.Q4_5;

public class TestThread extends Thread {
    public void run() {
        System.out.print("BEGIN");
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("END");
    }
}
