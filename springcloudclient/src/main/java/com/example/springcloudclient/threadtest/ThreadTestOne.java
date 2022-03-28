package com.example.springcloudclient.threadtest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:thinkjavapr1
 * @USER: 梁思禹
 * @DATE: 2022/2/11
 */
public class ThreadTestOne {

    public static void main(String[] args) {
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i=0; i < 5; i++){
            //Thread thread = new Thread(new RunTask1());
            executor.execute(new RunTask1());
        }
        executor.shutdown();
    }

}

class RunTask1 implements Runnable{

    private static int count = 0;

    RunTask1(){
        count ++;
        System.out.println("Thread Run - " + count);
    }


    @Override
    public void run() {
        for (int i = 0; i < 3; ++i){
            System.out.println(count + " - Run Thread - " + i);
            Thread.yield();
        }
        System.out.println("Thread Stop - " + count);
    }
}
