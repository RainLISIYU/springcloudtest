package com.example.springcloudclient.threadtest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:现场异常测试
 * @USER: 梁思禹
 * @DATE: 2022/3/5
 */
public class ThreadTestSix {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 30, 200L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(30));
        for (int i = 0; i < 10; ++i){
            try {
                threadPool.execute(()->{
                    new ThreadRunAbc().printA();
                });
                Thread.sleep(100L);
                threadPool.execute(()->{
                    new ThreadRunAbc().printB();
                });
                Thread.sleep(100L);
                threadPool.execute(()->{
                    new ThreadRunAbc().printC();
                });
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        threadPool.shutdown();


    }

}

class ThreadRunAbc{
    synchronized void printA(){
        System.out.println("A");
    }

    synchronized void printB(){
        System.out.println("B");
    }

    synchronized void printC(){
        System.out.println("C");
    }
}