package com.example.mockito.concurrency;

public class volatileDemo extends Thread{

    // 保证可见性，不保证原子性
    public static volatile int a = 0;


    public void test() throws InterruptedException {
        Thread demo1 = new Thread(new Thread1());
        Thread demo2 = new Thread(new Thread2());
        demo2.start();
        demo1.start();

        Thread.sleep(9000);
        System.out.println(a);
    }

    public class Thread1 implements Runnable{
        @Override
        public void run() {
            while (a<300000){
                if (a%2==0&&a<300000){
                    System.out.println(a);
                    a=a+1;
                }
            }
        }
    }

    public class Thread2 implements Runnable{
        @Override
        public void run() {
            while (a<300000){
                if (a%2==1&&a<300000){
                    System.out.println(a);
                    a=a+1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        volatileDemo volatileDemo = new volatileDemo();
        volatileDemo.test();
    }
}
