package com.example.mockito.classRelationship;

public class Son extends Father{

    public String name = "son";

    public static void print(){
        System.out.println("son");
    }

    public void printName(){
        System.out.println(name);
    }
}
