package com.example.mockito.multipleClasses;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Animal {

    abstract String name();

    String name = "dog";

    public void process(String name){
        if (name.equals(name())){
            this.doProcess();
        }
//        下面的代码不行，因为下面的name是父类的name(同名成员变量，因为是父类的引用，所以调的是父类的name)
//        if (name.equals(this.name)){
//            this.doProcess();
//        }
    }

    abstract void doProcess();
}
