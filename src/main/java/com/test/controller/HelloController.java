package com.test.controller;

import com.test.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloServiceImpl helloService;

    @RequestMapping("/test")
    public void sayHello() throws InterruptedException {
        String str = helloService.sayHello("wyd");
        // int i = 5 / 0;
        System.out.println("----正常controller--" + str);
    }
}
