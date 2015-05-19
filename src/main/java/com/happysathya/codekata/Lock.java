package com.happysathya.codekata;

import java.util.Date;

public class Lock {
    public void doWork() {
        System.out.println("just inside Thread-" + Thread.currentThread().getName() + " " + new Date().toString());
    }
}
