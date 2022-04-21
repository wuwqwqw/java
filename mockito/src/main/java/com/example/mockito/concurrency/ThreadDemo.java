package com.example.mockito.concurrency;

import lombok.AllArgsConstructor;

//轮流打印数字
@AllArgsConstructor
public class ThreadDemo extends Thread{

    public static int count = 0;

    public int index;

    public void run(){
        while (count<100000){
            // this不行，因为this有两个，每个线程都能拿到对象锁
            synchronized (ThreadDemo.class){
                if (count%2==index){
                    System.out.println(count);
                    ++count;
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo demo1 = new ThreadDemo(1);
        ThreadDemo demo0 = new ThreadDemo(0);
        demo0.start();
        demo1.start();
        System.out.println("异步");
    }
}
