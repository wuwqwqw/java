package com.example.mockito.concurrency;


public class ThreadDemo3 extends Thread{

    public static int i = 0;

    // 这里锁的是类对象
    public synchronized static void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0;j<50000;++j){
            increase();
        }
        System.out.println(i);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo3 threadDemo3 = new ThreadDemo3();
        ThreadDemo3 threadDemo31 = new ThreadDemo3();

        threadDemo31.start();
        threadDemo3.start();

        Thread.sleep(3000);
        System.out.println(i);
    }
}
