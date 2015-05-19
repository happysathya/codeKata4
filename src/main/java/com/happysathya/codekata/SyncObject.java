package com.happysathya.codekata;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class SyncObject {

    private final Lock lock;

    public SyncObject(Lock lock) {
        this.lock = lock;
    }

    public void startTwoThreads() throws InterruptedException {


        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch waitSignal = new CountDownLatch(2);


        Thread thread1 = new Thread(() -> {
            try {
                doWork(startSignal, waitSignal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                doWork(startSignal, waitSignal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        startSignal.countDown();

        System.out.println("just after startSignal.countDown()" + " " + new Date().toString());

        synchronized (lock) {
            lock.doWork();
        }

        waitSignal.await();

    }


    private void doWork(CountDownLatch startSignal, CountDownLatch waitSignal) throws InterruptedException {
        startSignal.await();

        System.out.println("just after startSignal.await() " + Thread.currentThread().getName() + " " + new Date().toString());

        synchronized (lock) {
            Thread.sleep(1000);
        }

        waitSignal.countDown();
        System.out.println("just after waitSignal.countDown() " + Thread.currentThread().getName() + " " + new Date().toString());
    }


}
