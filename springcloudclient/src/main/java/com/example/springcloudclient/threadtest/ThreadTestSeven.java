package com.example.springcloudclient.threadtest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:多线程list测试
 * @USER: 梁思禹
 * @DATE: 2022/3/6
 */
public class ThreadTestSeven {



    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> objects = new ArrayBlockingQueue<>(5);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 10, 200L, TimeUnit.MILLISECONDS, objects, new ThreadPoolExecutor.AbortPolicy());
        for(int i = 0; i < 13; i++){
            Thread thread = new MyThread(i+"");
            threadPool.execute(thread);
            System.out.println("队列size:"+objects.size());
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(10L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread extends Thread{

    MyThread(String name){
        super(name);
    }

    @Override
    public void run(){
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName());
    }

}
