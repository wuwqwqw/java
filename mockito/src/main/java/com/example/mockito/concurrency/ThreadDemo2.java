package com.example.mockito.concurrency;

import lombok.AllArgsConstructor;

//累加到100000
@AllArgsConstructor
public class ThreadDemo2 extends Thread{

    public static int count = 0;

    public void run(){
        for (int i = 0;i<5000000;++i){
            // 这里锁的是实例对象，等于没锁，所以结果不一定是10000000
            synchronized (this){
                ++count;
                System.out.println(count);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo2 demo1 = new ThreadDemo2();
        ThreadDemo2 demo2 = new ThreadDemo2();

        demo2.start();
        demo1.start();

        Thread.sleep(50000);
        System.out.println(count);
    }
}
