package com.example.mockito.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;


@Aspect
@Component
public class AddMetricAop {

    @Pointcut("@annotation(com.example.mockito.aop.AddMetric)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint){
        int[][] ints = new int[2][2];
        Arrays.sort(ints, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });
        Signature signature = joinPoint.getSignature();
        System.out.println("正在执行方法"+signature.getName());
    }

    @After("annotationPointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("执行完毕");
    }

}
