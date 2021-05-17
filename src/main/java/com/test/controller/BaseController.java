package com.test.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.ResponseBody;

@DefaultProperties(defaultFallback = "defaultFallback", ignoreExceptions = {}, commandProperties = {}, groupKey = "组名", threadPoolKey = "线程池名")
@ResponseBody
public abstract class BaseController {

    /*该方法必须是定义为静态*/
    public static void helloBlock(String str) {
        System.out.println("--熔断方法----" + str);

       // System.out.println("--timeout----" + te.getMessage());
    }

    //如果需要熔断的方法没有指定方法, 默认走defaultFallback方法
    public static void defaultFallback(Throwable te) {
        System.out.println("1111");
        System.out.println("--timeout----" + te.getMessage());
    }
/*    protected ResponseEntity<Object> defaultFallback2(Throwable e) {
        String devPrefix = "CircuitBreaker triggered:\r\n";
        String message = "系统繁忙，请稍后再试.";
        if (e != null) {
            // String errorDetail = devPrefix + ExceptionUtils.errorDetail(e);
            //LOG.error("执行hystrixMethod");
            // LOG.error(errorDetail);
            // message = isDevMode ? errorDetail : message;
            System.out.println("e != nul");
        }
        System.err.println(Thread.currentThread().getName());
        return ResponseEntity.badRequest().body(message);
    }*/
}

