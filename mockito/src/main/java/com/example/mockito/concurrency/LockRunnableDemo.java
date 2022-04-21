package com.example.mockito.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 从1加到100000
@Slf4j
public class LockRunnableDemo implements Runnable{

    private final Lock lock = new ReentrantLock();

    public int count = 0;

    public void run(){
        for (int i =0;i<500000;++i){
            synchronized (this){
                count = count + 1;
            }
            System.out.println(count);
        }
//        和下面效果一样，syncronized给对象加锁，保证只有一个线程能运行syncronized保护的代码，
//        lock是限制自己，保证只有一个线程持有lock，能运行lock保护的代码
//        for (int i =0;i<500000;++i){
//            try{
//                lock.lock();
//                count = count + 1;
//                System.out.println(count);
//            }catch (Exception e){
//                log.error(e.toString());
//            }finally {
//                lock.unlock();
//            }
//        }
    }

    public static void main(String[] args) {

        LockRunnableDemo lockDemo = new LockRunnableDemo();

        Thread thread = new Thread(lockDemo);
        Thread thread1 = new Thread(lockDemo);

        thread.start();
        thread1.start();

    }
}
