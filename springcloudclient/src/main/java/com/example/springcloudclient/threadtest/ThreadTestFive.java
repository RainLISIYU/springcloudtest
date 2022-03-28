package com.example.springcloudclient.threadtest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:现场测试
 * @USER: 梁思禹
 * @DATE: 2022/3/5
 */
public class ThreadTestFive {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Thread thread = new Thread(()->{
            Sub sub = new Sub();
            sub.operateNum();
        });
        executorService.execute(thread);
        executorService.shutdown();
    }
}

class Main{
    public int i = 10;
    synchronized public void operateNum(){
        try{
            i--;
            System.out.println("Main run i = " + i);
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Sub extends Main{
    synchronized public void operateNum(){
        try {
            while (i > 0){
                i--;
                System.out.println("sub run i = "+i);
                Thread.sleep(200);
                super.operateNum();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}