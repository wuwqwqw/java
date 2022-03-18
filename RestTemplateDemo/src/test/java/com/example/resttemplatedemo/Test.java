package com.example.resttemplatedemo;

import com.example.resttemplatedemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        String url = "http://localhost:8080/get";
        RestTemplate restTemplate = new RestTemplate();
        User forObject = restTemplate.getForObject(url,User.class);
        System.out.println(forObject);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        String url = "http://localhost:8080/get/zhaokai/2";
        RestTemplate restTemplate = new RestTemplate();
        User forObject = restTemplate.getForObject(url,User.class);
        System.out.println(forObject);
    }

    @org.junit.jupiter.api.Test
    public void test3(){
        String url = "http://localhost:8080/get2?id=3";
        RestTemplate restTemplate = new RestTemplate();
        User forObject = restTemplate.getForObject(url,User.class);
        System.out.println(forObject);
    }

    @org.junit.jupiter.api.Test
    public void test4(){
        String url = "http://localhost:8080/get3?id=3";
        RestTemplate restTemplate = new RestTemplate();
        User zhaokai = restTemplate.postForObject(url, new User("zhaokai", 20), User.class);
        System.out.println(zhaokai);
    }

    @org.junit.jupiter.api.Test
    public void testLog(){
        testLogStatic();
    }

    public static void testLogStatic(){
        log.warn("warn");
        log.warn("warn后不跳出");
        log.error("error");
        log.info("error后不跳出");
    }
}
