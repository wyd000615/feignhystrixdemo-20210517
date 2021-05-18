package com.test.controller;

import com.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/test")
    public void sayHello() throws InterruptedException {
        String str = helloService.sayHello("wyd");
        // int i = 5 / 0;
        System.out.println("----正常controller--" + str);
    }
}
