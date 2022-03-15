package com.example.springcloudclient.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * SpringCloudTest
 * jdk动态代理测试
 *
 * @author : Mr.L
 * @date : 2022-03-14 15:00
 **/
public class JdkProxyTest {
    public static void main(String[] args) {
        Car car = new Bus();
        ProxyHandle proxyHandle = new ProxyHandle(car);
        ClassLoader loader = car.getClass().getClassLoader();
        Class<?>[] interfaces = car.getClass().getInterfaces();
        Car obj = (Car) Proxy.newProxyInstance(loader, interfaces, proxyHandle);
        System.out.println(obj.run());
    }
}

class ProxyHandle implements InvocationHandler{

    private Car car;

    ProxyHandle(Car car){
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("car before run!");
        Object result = method.invoke(car, args);
        System.out.println("car after run!");
        return result;
    }
}

class Bus implements Car{

    @Override
    public String run() {
        return "Bus is Run!";
    }
}

interface Car {

    /**
     * 跑
     * @return
     */
    String run();

}
