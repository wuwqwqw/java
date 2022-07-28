package com.example.mockito.ThreadLocal;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class ThreadLocalDemo {

    public static ThreadLocal<HashMap<String,String>> map = new ThreadLocal<>();

    public static void main(String[] args) {
        HashMap<String,String> hashMap = map.get();
        hashMap.put("demo","demo");

    }
}
