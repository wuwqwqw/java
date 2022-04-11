package com.example.mockito.adapter;

public class Client {
    public static void main(String[] args) {

        Target target = new Adapter(new Adaptee());
        System.out.println(target.request());
    }
}
