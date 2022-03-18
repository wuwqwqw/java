package com.example.orderdemo.common;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 数值越小，优先级越高
@Component
@Order(0)
public class BlackPerson implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("black person goes first");
    }
}
