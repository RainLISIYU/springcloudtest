package com.example.springcloudclient.threadtest;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:sleep测试
 * @USER: 梁思禹
 * @DATE: 2022/2/14
 */
public class ThreadTestThree {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++) {
            executor.execute(new SleepThread());
        }
    }
}

class SleepThread implements Runnable{

    @Override
    public void run() {
        Random random = new Random();
        int i = random.nextInt(100)+1;
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep time is " + i);
    }
}
