package com.example.mockito.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer run = completableFutureDemo.run();
            System.out.println(run);
            return completableFutureDemo.run();
                });
        System.out.println("异步");
        Integer integer = completableFuture.get();
        System.out.println(integer);
    }

    public Integer run(){
        return 1;
    }
}
