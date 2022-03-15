package com.example.springcloudclient.test;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.reflect.Method;

/**
 * SpringCloudTest
 * cglib测试类
 *
 * @author : Mr.L
 * @date : 2022-03-14 15:39
 **/
public class CglibProxyTest {
    public static void main(String[] args) {
         Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyCar.class);
        enhancer.setCallback(new CarMethodInterceptor());
        ProxyCar obj = (ProxyCar) enhancer.create();
        obj.run();
    }
}

class ProxyCar {
    ProxyCar(){
        System.out.println("car create!");
    }

    public void run(){
        System.out.println("car is running!");
    }
}

class CarMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("car before run!");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("car after run!");
        return obj;
    }
}