package com.example.mockito.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo extends Thread{

    // 使用atomic类可以保证单个变量的线程安全
    public static AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0;i<50000;++i){
            integer.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicDemo demo = new AtomicDemo();
        AtomicDemo demo1 = new AtomicDemo();
        demo.start();
        demo1.start();
        Thread.sleep(2000);
        System.out.println(integer);
    }
}
