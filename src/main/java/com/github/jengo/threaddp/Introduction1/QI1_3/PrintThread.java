package com.github.jengo.threaddp.Introduction1.QI1_3;

public class PrintThread extends Thread {
    private String message;

    public PrintThread(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(message);
        }
    }
}
