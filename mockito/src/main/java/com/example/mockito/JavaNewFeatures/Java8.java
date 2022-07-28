package com.example.mockito.JavaNewFeatures;

import java.util.Optional;
import java.util.function.Consumer;

public class Java8 {

    public static void main(String[] args) {
        processStr("old");
        processStr(null);
    }


    public static void processStr(String str){

        //传函数式接口的实现，可以是匿名内部类，也可以是lambda
        Optional.ofNullable(str)
                .ifPresent(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        s= s.replace("old", "new");
                        System.out.println(s);
                    }
                });
    }
}
