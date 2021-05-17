package com.test.controller;

import org.springframework.stereotype.Component;

/**
 * 处理熔断方法的类
 */
@Component
public class DealException {
    /*该方法必须是定义为静态*/
    public static  void helloBlock(Throwable te ) {
        System.out.println("--timeout----"+te.getMessage());
    }
}
