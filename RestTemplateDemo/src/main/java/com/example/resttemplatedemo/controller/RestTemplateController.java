package com.example.resttemplatedemo.controller;


import com.example.resttemplatedemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RestTemplateController {

    // 全局异常处理
    @ExceptionHandler(Throwable.class)
    @GetMapping(value = "/get")
    @ResponseBody
    public User getUser(){
        User xiaMing = new User("XiaMing",2);
        int a=2/0;
        return xiaMing;
    }

    @GetMapping(value = "/get/{name}/{id}")
    @ResponseBody
    public User getUserByName(@PathVariable String name,@PathVariable Integer id){
        User xiaMing = new User(name,id);
        return xiaMing;
    }

    @GetMapping(value = "/get2")
    @ResponseBody
    public User getUserById(@RequestParam("id") Integer id){
        User xiaMing = new User("XiaMing",id);
        return xiaMing;
    }


    @PostMapping(value = "/get3")
    @ResponseBody
    public User getUserPost(@RequestBody User user){
        return user;
    }
}
