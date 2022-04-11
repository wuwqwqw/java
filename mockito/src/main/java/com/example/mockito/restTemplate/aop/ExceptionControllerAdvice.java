package com.example.mockito.restTemplate.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(Throwable.class)
    public void exception(RuntimeException exception){
        log.error("将终止程序",exception);
    }
}
