package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class HystrixController extends BaseController {
    @RequestMapping("/test/{str}")
    @HystrixCommand(fallbackMethod = "helloBlock",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public void test(@PathVariable String str) throws InterruptedException {
        //System.out.println(Thread.currentThread().getId());
        //System.out.println(Thread.currentThread().getName());
        System.out.println("===原方法中str==="+str);
        // int i = 5 / 0;
        Thread.sleep(3000);
        System.out.println("------");
    }
}
