package com.example.mockito.concurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncDemo {

    @Autowired
    ExecutePoolConfiguration executePoolConfiguration;

    @Async("threadPoolTaskExecutor")
    public void async() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName());
        System.out.println("异步");
    }

    @Async
    public Future<Integer> asyncWithResult() throws InterruptedException {
        Thread.sleep(3000);
        return new AsyncResult<Integer>(999);
    }

}
