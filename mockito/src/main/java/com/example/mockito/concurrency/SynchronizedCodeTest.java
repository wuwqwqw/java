package com.example.mockito.concurrency;

public class SynchronizedCodeTest {

    public static void main(String[] args) {
        SynchronizedCodeTest synchronizedCodeTest = new SynchronizedCodeTest();
        for (int i = 0; i < 5; i ++) {
            Thread thread = new Thread(() -> {
                synchronizedCodeTest.testSynchronizedCode();
            });
            thread.start();
        }
    }

    int count = 0;
    public void testSynchronizedCode() {
        System.out.printf("%s-testSynchronizedCode-start-count=%s\n", Thread.currentThread().getName(), count);
        synchronized (this) {
            System.out.printf("%s-testSynchronizedCode-synchronized-start-count=%s\n", Thread.currentThread().getName(), count);
            count ++;
            System.out.printf("%s-testSynchronizedCode-synchronized-end-count=%s\n", Thread.currentThread().getName(), count);
        }
        System.out.printf("%s-testSynchronizedCode-end-count=%s\n", Thread.currentThread().getName(), count);
    }

}

