package com.example.mockito.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AddMetricAop {

    @Pointcut("@annotation(com.example.mockito.aop.AddMetric)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("正在执行方法"+signature.getName());
    }

    @After("annotationPointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("执行完毕");
    }

}
