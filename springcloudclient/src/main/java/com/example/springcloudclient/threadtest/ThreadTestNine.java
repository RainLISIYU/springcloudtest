package com.example.springcloudclient.threadtest;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:生成消费测试
 * @USER: 梁思禹
 * @DATE: 2022/3/6
 */
public class ThreadTestNine {

    public static void main(String[] args) {
        ClassLoader classLoader = ThreadTestNine.class.getClassLoader();
        try {
            Iterator<URL> itr = classLoader.getResources("./").asIterator();
            while (itr.hasNext()){
                System.out.println(itr.next());
            }
            System.out.println(ClassLoader.getSystemResource("./"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RunMethod runMethod = new RunMethod();
        for (int i = 0 ; i < 10; i++){
            Thread thread1 = new Thread(()->runMethod.printA());
            Thread thread2 = new Thread(()->runMethod.printB());
            Thread thread3 = new Thread(()->runMethod.printC());
            thread1.start();
            thread3.start();
            thread2.start();
        }
    }

}

class RunMethod {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private int i = 0;
    synchronized void printA(){

        while (i != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i=1;
        this.notifyAll();
    }

    synchronized void printB(){
        while (i != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 2;
        this.notifyAll();
    }
    synchronized void printC(){
        while (i != 2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 0;
        this.notifyAll();
    }
}
