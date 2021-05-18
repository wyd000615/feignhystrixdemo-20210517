package com.test.controller;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SystemLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SystemLogAspect.class);


    @Pointcut("execution(* com.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doInvoke(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LOG.error(throwable.getMessage(), throwable);
            throw new RuntimeException(throwable);
        } finally {
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;

            printLog(pjp, result, elapsedTime);

        }

        return result;
    }

    /**
     * 打印日志
     *
     * @param pjp         连接点
     * @param result      方法调用返回结果
     * @param elapsedTime 方法调用花费时间
     */
    private void printLog(ProceedingJoinPoint pjp, Object result, long elapsedTime) {


    }

    /**
     * 获取注解
     */
    //todo

}