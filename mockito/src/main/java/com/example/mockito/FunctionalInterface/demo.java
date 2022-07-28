package com.example.mockito.FunctionalInterface;

//函数式接口作为方法的参数
public class demo {

    public static void main(String[] args) {
        //使用lambda传递
        startThread(()-> System.out.println(Thread.currentThread().getName()+"线程启动了1"));

        //使用匿名类来传递
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程启动了2");
            }
        });
    }

    //接收一个函数式接口，可以用匿名类来传递，也可以用lambda表达式来传递
    private static void startThread(Runnable r){
        new Thread(r).start();
    }
}
