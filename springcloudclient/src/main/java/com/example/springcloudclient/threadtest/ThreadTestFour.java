package com.example.springcloudclient.threadtest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:线程测试4
 * @USER: 梁思禹
 * @DATE: 2022/2/15
 */
public class ThreadTestFour {

    public static void main(String[] args) {
        Thread thread = new Thread(new SimpleDaemon());
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(188);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SimpleDaemon implements Runnable{

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(Thread.currentThread() + " : " + this);
            }
        }catch (InterruptedException e){
            System.out.println("");
        }


    }
}
