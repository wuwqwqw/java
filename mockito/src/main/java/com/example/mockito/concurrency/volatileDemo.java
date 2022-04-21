package com.example.mockito.concurrency;

public class volatileDemo extends Thread{

    public static volatile int a = 0;

    @Override
    public void run() {
        for (int i = 0;i<50000;++i){
            ++a;
        }
        System.out.println(a+Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        volatileDemo demo = new volatileDemo();
        volatileDemo demo1 = new volatileDemo();
        demo.start();
        demo1.start();
    }
}
