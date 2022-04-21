package com.example.mockito.concurrency;

import lombok.AllArgsConstructor;

//累加到100000
@AllArgsConstructor
public class ThreadDemo2 extends Thread{

    public static int count = 0;

    public void run(){
        for (int i = 0;i<50000;++i){
            synchronized (this){
                ++count;
                System.out.println(count);
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo2 demo1 = new ThreadDemo2();
        ThreadDemo2 demo2 = new ThreadDemo2();

        demo2.start();
        demo1.start();

        System.out.println("异步");
    }
}
