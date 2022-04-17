package com.wakaka.cases.thread;

import java.io.File;
import java.io.OutputStreamWriter;

public class Try extends Thread {
    public static void main(String[] args) {
        Try t = new Try();
        t.start();
    }


    public void run() {//run(int j)  cannot print
        int i = 0;
        while (i < 5) {
            System.out.println("print");
            i++;
        }
    }

}
