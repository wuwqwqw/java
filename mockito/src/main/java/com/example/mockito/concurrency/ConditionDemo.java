package com.example.mockito.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionDemo implements Runnable{


    @Override
    public void run() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            System.out.println(lock.isLocked());
            condition.await();
        } catch (InterruptedException e) {
            log.warn(e.toString());
        }finally {
            condition.signal();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        Thread thread = new Thread(demo);
        thread.start();
    }
}
