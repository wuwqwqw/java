package com.example.mockito.classRelationship;

import org.junit.Test;

public class test {

    @Test
    public void test(){
        Father son = new Son();
        son.printName();
        System.out.println(son.name);
        Son.print();
    }
}
