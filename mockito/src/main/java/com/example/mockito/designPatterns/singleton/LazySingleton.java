package com.example.mockito.designPatterns.singleton;

public class LazySingleton {

    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        if (null == lazySingleton){
            synchronized (LazySingleton.class){
                if (null == lazySingleton){
                    // 这里标黄提示的原因 ： 因为new对应的Class中只包含了静态变量，静态方法，idea认为你没有必要去new出来一个对象，所以进行了提示。
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
//1、if第一次判断是为了预先检查一下是否被创建，如果被创建了就没必要加锁了直接返回。
//2、if第二次判断为了防止线程1刚进来if语句，另一个线程也进来了并加锁创建好了实例。
//3、volatile： 线程1分配了地址，但还没创建好对象，线程2判断为非空就返回了
