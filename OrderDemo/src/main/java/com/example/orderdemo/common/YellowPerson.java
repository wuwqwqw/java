package com.example.orderdemo.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class YellowPerson implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("yellow person goes after black person");
    }
}
