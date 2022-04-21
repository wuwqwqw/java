package com.example.mockito.multipleClasses;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Cat extends Animal{

    @Override
    String name() {
        return this.name;
    }

    String name;

    @PostConstruct
    public void init(){
        this.name = "cat";
    }

    @Override
    void doProcess() {
        System.out.println("i am a cat");
    }
}
