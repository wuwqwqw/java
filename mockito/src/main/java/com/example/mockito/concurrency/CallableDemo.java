package com.example.mockito.concurrency;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
@AllArgsConstructor
public class CallableDemo implements Callable<Integer> {

    public int index;

    @Override
    public Integer call() throws Exception {
        return index*100;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo(1);
        CallableDemo callableDemo1 = new CallableDemo(2);

        FutureTask<Integer> task1 = new FutureTask<Integer>(callableDemo);
        FutureTask<Integer> task2 = new FutureTask<Integer>(callableDemo1);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        log.info("异步");
        try {
            System.out.println(task1.get()+task2.get());
        }catch (ExecutionException | InterruptedException e){
            log.error(e.toString());
        }

        log.info("同步");
    }
}
