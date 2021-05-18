package com.test.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.controller.BaseController;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl  extends BaseController    {
    @HystrixCommand(fallbackMethod = "helloBlock",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })


    public String sayHello(String str) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("我是正常方法" + str);

        return "正常" + str;
    }
}
