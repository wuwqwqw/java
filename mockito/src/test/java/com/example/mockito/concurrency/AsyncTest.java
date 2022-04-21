package com.example.mockito.concurrency;

import com.example.mockito.multipleClasses.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@EnableAsync
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class AsyncTest {

    @Autowired
    AsyncDemo asyncDemo;


    @Test
    public void test() throws InterruptedException {
        asyncDemo.async();
        System.out.println("先执行下面的");
        Thread.sleep(5000);
    }

    @Test
    public void testWithResult() throws InterruptedException, ExecutionException {
        Future<Integer> future = asyncDemo.asyncWithResult();
        System.out.println("先执行下面的");
        Integer integer = future.get();
        System.out.println(integer);
    }

    @Autowired
    List<Animal> animals;

    @Test
    public void testAnimal(){
        for (Animal animal:animals){
            animal.process("dog");
        }
    }
}
