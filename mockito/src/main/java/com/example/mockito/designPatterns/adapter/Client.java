package com.example.mockito.designPatterns.adapter;

public class Client {
    public static void main(String[] args) {

        Target target = new Adapter(new Adaptee());
        System.out.println(target.request());
    }
}
