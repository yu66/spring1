package com.luban.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.luban")

//默认为false 即为jdk动态代理   jdk动态代理继承了Proxy类，所以是基于接口实现的 本例是实现了UserDao接口
//若为true,则是cglib代理
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AppConfig {
}
