package com.github.jengo.threaddp.AppendixB.DoubleCheckedLocking;

public class Main {

    public static void main(String[] args) {
        // 线程A
        new Thread() {
            @Override
            public void run() {
                System.out.println(MySystem.getInstance().getDate());
            }
        }.start();

        // 线程B
        new Thread() {
            @Override
            public void run() {
                System.out.println(MySystem.getInstance().getDate());
            }
        }.start();
    }

}
