package com.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.test.controller.BaseController;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
//@FeignClient(value = "myFeignClient", fallback = BaseController.class)
public interface HelloService {
    @RequestMapping(method = RequestMethod.POST)
    String sayHello(String str) throws InterruptedException;

}
