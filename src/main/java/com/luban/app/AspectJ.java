package com.luban.app;

import com.luban.dao.UserDao;
import com.luban.dao.UserDaoImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

//@Aspect("perthis(this(com.luban.dao.UserDaoImpl))") 指定使用原型的种类 和@Scope("prototype") 一起使用
@Aspect()
@Component("aspect")

public class AspectJ {

    @DeclareParents(value = "com.luban.dao.*",defaultImpl = UserDaoImpl.class)
    public static UserDao dao;

    @Pointcut("execution(* com.luban..*.*(..))")
    public void pointcutExecution(){ }
    @Pointcut("within(*com.luban..*)")
    public void pointcutWithin(){}
    @Pointcut("args(java.lang.String)")
    public void pointcutArgs(){}
    @Pointcut("this(com.luban.dao.UserDaoImpl)")
    public void pointcutThis(){}
    @Pointcut("target(com.luban.dao.UserDaoImpl)")
    public void pointcutTarget(){}
    @Pointcut("@annotation(com.luban.Anno.Test)")
    public void pointcutAnno(){}

    @Before("pointcutExecution() && pointcutThis()")
    public void adviceBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getThis() instanceof Proxy);
        System.out.println(joinPoint.getTarget() instanceof UserDaoImpl);
        System.out.println("before1");
    }
    @After("pointcutAnno()")
    public void adviceAfter(){
        System.out.println("使用注解的连接点（方法） 增强了");

    }
    @Around("pointcutWithin()")
//    ProceedingJoinPoint 处理 连接点
    public void adviceAround(ProceedingJoinPoint pjp){
        System.out.println("before");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after");
    }

}
