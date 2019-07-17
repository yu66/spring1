package com.luban.test;

import com.luban.app.AppConfig;
import com.luban.dao.UserDao;
import com.luban.dao.UserDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  annotationConfigApplicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao= (UserDao) annotationConfigApplicationContext.getBean("indexDao");

        UserDao introDao= (UserDao) annotationConfigApplicationContext.getBean("introDao");
        //实现了接口UserDao  实现用的是 UserDaoImpl 里面的方法 ，不是继承UserDaoImpl 没有使用代理
        System.out.println(introDao instanceof UserDaoImpl);
        introDao.query2();
        System.out.println("-------------");

        System.out.println(userDao instanceof UserDaoImpl);
        System.out.println(userDao instanceof Proxy);
        System.out.println("--------------------------------");
        userDao.query();
        //切面Bean
        System.out.println(annotationConfigApplicationContext.getBean("aspect").hashCode());
        System.out.println("--------------");
        userDao.query2();
        //切面Bean  由于Aspect类是单例 所以和上面的hashCode一样
        System.out.println(annotationConfigApplicationContext.getBean("aspect").hashCode());





    }
}
