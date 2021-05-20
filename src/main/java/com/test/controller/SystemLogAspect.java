package com.test.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
@Aspect
public class SystemLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SystemLogAspect.class);
    private Map<Long, Map<String, List<Long>>> threadMap = new ConcurrentHashMap<>(200);


    @Pointcut("execution(* com.test.service.impl.HelloServiceImpl.sayHello(..))")
    public void pointcut() {
    }
    @Before("pointcut()")
    public void beforeFun(JoinPoint joinPoint){
        System.out.println("我是前置打印方法");
        System.out.println(joinPoint.toShortString() + " 开始");
        System.out.println(joinPoint.toString() + " 开始");

        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        List<Long> list;
        if (methodTimeMap == null) {
            methodTimeMap = new HashMap<>();
            list = new LinkedList<>();
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
            threadMap.put(Thread.currentThread().getId(), methodTimeMap);
        } else {
            list = methodTimeMap.get(joinPoint.toShortString());
            if (list == null) list = new LinkedList<>();
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
        }


    }
    @After("pointcut()")
    public void afterFun(JoinPoint joinPoint){
        System.out.println("我是后置打印方法");
        System.out.println(joinPoint.toShortString() + " 结束");
        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        List<Long> list = methodTimeMap.get(joinPoint.toShortString());
        System.out.println("耗时：" + (System.currentTimeMillis() - list.get(list.size() - 1)));
        list.remove(list.size() - 1);


    }
    /**
     * 针对调用的接口（日志的形式）观察执行时间
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        log.info("执行方法：{},开始时间:{}" , joinPoint.getSignature().getName() , System.currentTimeMillis());
        Object result = joinPoint.proceed();
        long endTime= System.currentTimeMillis();
        log.info("执行方法：{}， 用时：{} 毫秒, 结束时间:{} , " , joinPoint.getSignature().getName() ,  endTime-startTime ,  System.currentTimeMillis());
        return  result  ;
    }


}