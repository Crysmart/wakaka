package com.wakaka.framework.spring4.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop config
 * @author Crysmart
 * @date 2020/7/1 14:32
 */
@Aspect
@Component
public class AopConfig {

    /**
     * 为Bean引入新功能
     * {@link DeclareParents} 是Aop的一个添加新方法的注解
     * {@param value} 参数支持通配符
     * {@param defaultImpl} 为AopInterface的实现类
     * value = "com.wakaka.framework.spring4.JavaBean+"：+ 表示JavaBean的所有子类
     */
    @DeclareParents(value = "com.wakaka.framework.spring4.JavaBean+",
            defaultImpl = AopClass.class)
    public AopInterface aopInterface;

    @Pointcut("execution(* com.wakaka.framework.spring4.aop.*.*(..))")
    public void aspectFunc(){

    }

    @Around("aspectFunc()")
    public Object aroundFunc(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("来了老铁");
        Object proceed = pjp.proceed();
        System.out.println("走了老铁");
        return proceed;
    }
}
