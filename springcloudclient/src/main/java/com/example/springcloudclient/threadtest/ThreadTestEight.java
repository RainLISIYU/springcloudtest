package com.example.springcloudclient.threadtest;

/**
 * @Description:休眠唤醒测试
 * @USER: 梁思禹
 * @DATE: 2022/3/6
 */
public class ThreadTestEight {

    public static void main(String[] args) {
        RunMethodTest runMethodTest = new RunMethodTest();
        Thread thread1 = new Thread(new Thread1(runMethodTest));
        Thread thread2 = new Thread(new Thread2(runMethodTest));
        thread1.start();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        System.out.println("12345".substring(2,3));
    }
}

class Thread1 implements Runnable{

    private RunMethodTest runMethodTest;

    Thread1(RunMethodTest runMethodTest){
        this.runMethodTest = runMethodTest;
    }

    @Override
    public void run() {
        runMethodTest.runMethod1();
    }
}

class Thread2 implements Runnable{

    private RunMethodTest runMethodTest;

    Thread2(RunMethodTest runMethodTest){
        this.runMethodTest = runMethodTest;
    }

    @Override
    public void run() {
        runMethodTest.runMethod2();
    }
}

class RunMethodTest{
    final Object object = new Object();
    void runMethod1(){
        synchronized (object){
            System.out.println("wait is start");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait is end");
        }
    }
    void runMethod2(){
        synchronized (object){
            System.out.println("notify is start");
            object.notify();
            System.out.println("notify is end");
        }
    }
}
