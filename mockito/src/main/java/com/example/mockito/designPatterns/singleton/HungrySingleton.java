package com.example.mockito.designPatterns.singleton;

public class HungrySingleton {

    private static final HungrySingleton singleton = new HungrySingleton();

    public HungrySingleton getInstance(){
        return singleton;
    }

    private HungrySingleton(){

    }
}
